package com.bravedroid.data.repository

import android.annotation.TargetApi
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bravedroid.data.local.model.StoryLocal
import com.bravedroid.data.local.service.LocalPersistence
import com.bravedroid.data.local.service.LocalPersistenceImpl
import com.bravedroid.data.network.service.HookedNetworkServiceFactory
import com.bravedroid.data.util.Encoder64
import com.bravedroid.data.util.transformToMessages
import com.bravedroid.data.util.transformToStory
import com.bravedroid.domain.*
import com.bravedroid.usecases.repository.Repository
import timber.log.Timber
import java.time.Duration
import java.time.Instant

class RepositoryImpl(context: Context) : Repository {

    private val localPersistence: LocalPersistence = LocalPersistenceImpl(context)

    override fun getStory(storyId: String): LiveData<SubmitUiModel<Story>> {
        val liveData = MutableLiveData<SubmitUiModel<Story>>()
        liveData.value = createSubmitUiModel(SubmitUiModel.ResponseState.LOADING)

        if (!isLocalDataExist(storyId)) {
            Timber.d("isLocalDataExist FALSE")
            Timber.d("requestNetwork")
            requestNetwork(liveData, storyId)
        } else {
            if (isLocalDataUpToDate(storyId)) {
                Timber.d("isLocalDataUpToDate TRUE")
                requestLocalPersistence(storyId, liveData)
            } else {
                Timber.d("isLocalDataUpToDate FALSE")
                Timber.d("requestNetwork")
                requestNetwork(liveData, storyId)
            }
        }
        return liveData
    }

    private fun requestLocalPersistence(
        storyId: String,
        liveData: MutableLiveData<SubmitUiModel<Story>>
    ) {
        val storyLocal = localPersistence.getStoryLocalById(storyId)!!
        liveData.value = createSubmitUiModel(
            SubmitUiModel.ResponseState.SUCCESS, transformToStory(storyLocal)
        )
    }

    private fun requestNetwork(
        liveData: MutableLiveData<SubmitUiModel<Story>>,
        storyId: String
    ) {
        if (hasInternetConnection()) {
            Timber.d("hasInternetConnection :TRUE")
            fetchStory(liveData, storyId, getUser(), localPersistence)
        } else {
            Timber.d("hasInternetConnection :FALSE")
            liveData.value =
                createSubmitUiModel(SubmitUiModel.ResponseState.ERROR, null, NoInternetResponseError())
        }
    }

    private fun getUser() = User(login = "test", password = "VF62pqDX")

    private fun fetchStory(
        liveData: MutableLiveData<SubmitUiModel<Story>>,
        storyId: String,
        user: User,
        localPersistence: LocalPersistence
    ) {
        WorkerAsyncTask(liveData, storyId, user, localPersistence).execute()
    }

    override fun getMessageListByStory(storyId: String): List<Message> {
        val storyLocal = localPersistence.getStoryLocalById(storyId)!!
        return storyLocal.messages
    }

    private fun isLocalDataExist(storyId: String): Boolean =
        localPersistence.getLastFetchInstant(storyId) != -1L

    @TargetApi(Build.VERSION_CODES.O)
    private fun isLocalDataUpToDate(storyId: String): Boolean {
        val last = Instant.ofEpochMilli(localPersistence.getLastFetchInstant(storyId))
        val now = Instant.now()
        val duration = Duration.between( last,now)
        Timber.d("isLocalDataUpToDate duration: ${duration.seconds}:s ${duration.toMinutes()}:m")
        return duration.toMinutes() < 2
    }

    private fun hasInternetConnection(): Boolean = true

    private class WorkerAsyncTask(
        private val liveData: MutableLiveData<SubmitUiModel<Story>>,
        private val storyId: String,
        private val user: User,
        private val localPersistence: LocalPersistence
    ) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val networkService = HookedNetworkServiceFactory().create()
            val response = networkService.getStoryDto(generateAuthorizationHeaderValue(), storyId).execute()
            val body = response.body()

            if (response.isSuccessful) {
                val story = transformToStory(body!!)
                val messages = transformToMessages(body!!)
                val storyLocal: StoryLocal =
                    StoryLocal(story.id, story.title, story.description ?: "", story.urlImageCover, messages)
                localPersistence.saveOrUpdate(storyLocal)

                liveData.postValue(createSubmitUiModel(SubmitUiModel.ResponseState.SUCCESS, story))
            } else {
                liveData.postValue(createSubmitUiModel(SubmitUiModel.ResponseState.ERROR, null, ServerResponseError()))
            }
            return null
        }


        private fun generateAuthorizationHeaderValue(): String {
            val encodeTo64 = Encoder64.encodeTo64("${user.login}:${user.password}")
            return "Basic $encodeTo64"
        }
    }
}
