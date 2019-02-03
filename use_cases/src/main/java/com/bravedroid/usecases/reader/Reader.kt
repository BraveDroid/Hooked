package com.bravedroid.usecases.reader

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.domain.SubmitUiModel
import com.bravedroid.usecases.repository.Repository

class Reader(private val repository: Repository) {
    fun getStory(storyId:String): LiveData<SubmitUiModel<Story>> = repository.getStory(storyId)
    fun getMessagesForStory(storyId: String): LiveData<SubmitUiModel<List<Message>>> = repository.getMessageListByStory(storyId)
}
