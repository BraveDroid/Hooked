package com.bravedroid.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bravedroid.data.network.service.HookedNetworkServiceFactory
import com.bravedroid.data.util.Encoder64
import com.bravedroid.data.util.transformToStory
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.domain.User
import com.bravedroid.usecases.repository.Repository

class RepositoryImpl : Repository {

    override fun getStory(storyId: String): LiveData<Story> {
        return if (mustCallNetwork()) {
            val liveData = MutableLiveData<Story>()
            if (hasInternetConnection()) {
                fetchStory(liveData, storyId, getUser())
            }
            liveData
        } else
            TODO("mazeeeel nekhdem feha")
    }

    private fun getUser() = User(login = "test", password = "VF62pqDX")

    private fun fetchStory(liveData: MutableLiveData<Story>, storyId: String, user: User) {
        WorkerAsyncTask(liveData, storyId, user).execute()
    }

    override fun getMessageListByStory(storyId: String): LiveData<List<Message>> {
        TODO("asbbar shwaya ")
    }

    private fun mustCallNetwork(): Boolean = !isLocalDataExist() || !isLocalUpToDate()
    private fun isLocalDataExist(): Boolean = false
    private fun isLocalUpToDate(): Boolean = false
    private fun hasInternetConnection(): Boolean = true

    private class WorkerAsyncTask(
        private val liveData: MutableLiveData<Story>,
        private val storyId: String,
        private val user: User
    ) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val networkService = HookedNetworkServiceFactory().create()
            val response = networkService.getStoryDto(generateAuthorizationHeaderValue(), storyId).execute()
            val body = response.body()
            if (response.isSuccessful) {
                liveData.postValue(transformToStory(body!!))
            }

            //else throw RuntimeException()

            return null
        }

        private fun generateAuthorizationHeaderValue(): String {
            val encodeTo64 = Encoder64.encodeTo64("${user.login}:${user.password}")
            return "Basic $encodeTo64"
        }
    }
}
