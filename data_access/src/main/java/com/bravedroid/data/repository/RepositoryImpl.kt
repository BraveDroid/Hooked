package com.bravedroid.data.repository

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
import java.util.*
import android.net.ConnectivityManager


class RepositoryImpl(private val context: Context) : Repository {

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
        if (hasInternetConnection(context)) {
            Timber.d("hasInternetConnection :TRUE")
            fetchStory(liveData, storyId, getUser(), localPersistence)
        } else {
            Timber.d("hasInternetConnection :FALSE")
            liveData.value =
                createSubmitUiModel(SubmitUiModel.ResponseState.ERROR, null, NoInternetResponseError())
        }
    }

    /**
     * user could be stored in a db
     */
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

    private fun isLocalDataUpToDate(storyId: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val last = Instant.ofEpochMilli(localPersistence.getLastFetchInstant(storyId))
            val now = Instant.now()
            val duration = Duration.between(last, now)
            Timber.d("isLocalDataUpToDate duration: ${duration.seconds}:s ${duration.toMinutes()}:m")
            return duration.toMinutes() < 2
        } else {
            val last = (localPersistence.getLastFetchInstant(storyId))
            val now = Date().time
            val duration = now - last
            Timber.d("isLocalDataUpToDate duration: ${duration % 60}:s ${duration / (60 * 1000)}:m")
            return (duration / (60 * 1000)) < 2
        }
    }

    private fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var isConnected = false
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return isConnected
    }

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
