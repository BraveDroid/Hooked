package com.bravedroid.usecases.repository

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story

interface Repository {
    fun getStory(storyId: String): LiveData<Story>
    fun getMessageListByStory(storyId: String): LiveData<List<Message>>
}
