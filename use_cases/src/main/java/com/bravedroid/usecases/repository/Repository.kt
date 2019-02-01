package com.bravedroid.usecases.repository

import androidx.lifecycle.LiveData
import com.bravedroid.domain.Message
import com.bravedroid.domain.Story

interface Repository {
    fun getStory(): LiveData<Story>
    fun getMessageListByStory(storyId: Int): LiveData<List<Message>>
}
