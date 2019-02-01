package com.bravedroid.usecases.reader

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.usecases.repository.Repository

class Reader(private val repository: Repository) {
    fun getStory(storyId: Int = 1): LiveData<Story> = repository.getStory()
    fun getMessagesForStory(storyId: Int = 1): LiveData<List<Message>> = repository.getMessageListByStory(storyId)
}
