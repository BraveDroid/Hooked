package com.bravedroid.data.local.service

import com.bravedroid.data.local.model.StoryLocal

interface LocalPersistence {
    fun saveOrUpdate(story: StoryLocal)
    fun getStoryLocalById(storyId: String): StoryLocal?
    fun getLastFetchInstant(storyId: String): Long
}
