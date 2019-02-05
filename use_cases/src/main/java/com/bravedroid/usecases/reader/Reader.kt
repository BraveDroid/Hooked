package com.bravedroid.usecases.reader

import androidx.lifecycle.LiveData
import com.bravedroid.domain.model.Message
import com.bravedroid.domain.model.Story
import com.bravedroid.usecases.model.SubmitUiModel
import com.bravedroid.usecases.repository.Repository

class Reader(private val repository: Repository) {
    fun getStory(storyId:String): LiveData<SubmitUiModel<Story>> = repository.getStory(storyId)
    fun getMessagesForStory(storyId: String):  List<Message> = repository.getMessageListByStory(storyId)
}
