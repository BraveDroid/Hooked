package com.bravedroid.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.usecases.repository.Repository

class RepositoryImpl : Repository {
    override fun getStory(): LiveData<Story> {
        val liveData = MutableLiveData<Story>()
        loadStory(liveData)
        return liveData
    }

    private fun loadStory(liveData: MutableLiveData<Story>) {
        WorkerAsyncTask(liveData).execute();

    }

    override fun getMessageListByStory(storyId: Int): LiveData<List<Message>> {
        TODO("asbbar shwaya ")
    }

    private class WorkerAsyncTask(private val liveData: MutableLiveData<Story>) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            liveData.postValue(
                Story(
                    1,
                    "title Fat7iz",
                    "description my man",
                    "https://voiture.kidioui.fr/image/lexique/sw.jpg"
                )
            )
            return null
        }
    }
}
