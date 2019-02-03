package com.bravedroid.data.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bravedroid.data.local.model.StoryStruct
import com.bravedroid.data.local.service.LocalPersistence
import com.bravedroid.data.local.service.LocalPersistenceImpl
import com.bravedroid.data.network.service.HookedNetworkServiceFactory
import com.bravedroid.data.util.Encoder64
import com.bravedroid.data.util.transformToMessages
import com.bravedroid.data.util.transformToStory
import com.bravedroid.domain.*
import com.bravedroid.usecases.repository.Repository
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class RepositoryImpl(context: Context) : Repository {
    private val localPersistence: LocalPersistence = LocalPersistenceImpl(context)

    override fun getStory(storyId: String): LiveData<SubmitUiModel<Story>> {
        val liveData = MutableLiveData<SubmitUiModel<Story>>()
        liveData.value = createSubmitUiModel(SubmitUiModel.ResponseState.LOADING)

        if (mustCallNetwork(storyId)) {
            if (hasInternetConnection()) {
                fetchStory(liveData, storyId, getUser(), localPersistence)
            }
        } else {
            if (isLocalDataExist(storyId)) {
                liveData.value = createSubmitUiModel(
                    SubmitUiModel.ResponseState.SUCCESS,
                    transformToStory(localPersistence.getById(storyId)!!)
                )
            } else {
                liveData.value = createSubmitUiModel(SubmitUiModel.ResponseState.ERROR, null, NoInternetResponseError())
            }
        }
        return liveData

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

    override fun getMessageListByStory(storyId: String): LiveData<SubmitUiModel<List<Message>>> {
        TODO("asbbar shwaya ")
    }

    private fun mustCallNetwork(storyId: String): Boolean = !isLocalDataExist(storyId) || !isLocalUpToDate(storyId)
    private fun isLocalDataExist(storyId: String): Boolean {
       localPersistence.getById(storyId) ?: return false
        return true
    }

    //gonna be changed later based on some conditions
    private fun isLocalUpToDate(storyId: String): Boolean =  isLocalDataExist(storyId)
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
                val storyStruct: StoryStruct =
                    StoryStruct(story.id, story.title, story.description ?: "", story.urlImageCover, messages)
                localPersistence.saveOrUpdate(storyStruct)

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

fun createSubmitUiModel(
    responseState: SubmitUiModel.ResponseState?,
    story: Story? = null,
    error: ResponseError? = null
): SubmitUiModel<Story> =
    SubmitUiModel(responseState!!, story, error)
