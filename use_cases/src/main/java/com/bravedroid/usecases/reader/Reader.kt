package com.bravedroid.usecases.reader

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.usecases.repository.Repository

class Reader(private val repository: Repository) {
    fun getStory(storyId:String): LiveData<Story> = repository.getStory(storyId)
    fun getMessagesForStory(storyId: String): LiveData<List<Message>> = repository.getMessageListByStory(storyId)
}
