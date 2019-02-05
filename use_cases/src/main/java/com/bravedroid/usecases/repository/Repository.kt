package com.bravedroid.usecases.repository

import androidx.lifecycle.LiveData
import com.bravedroid.domain.model.Message
import com.bravedroid.domain.model.Story
import com.bravedroid.usecases.model.SubmitUiModel

interface Repository {
    fun getStory(storyId: String): LiveData<SubmitUiModel<Story>>
    fun getMessageListByStory(storyId: String):  List<Message>
}
