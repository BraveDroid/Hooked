package com.bravedroid.usecases.repository

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story
import com.bravedroid.domain.SubmitUiModel

interface Repository {
    fun getStory(storyId: String): LiveData<SubmitUiModel<Story>>
    fun getMessageListByStory(storyId: String): LiveData<SubmitUiModel<List<Message>>>
}
