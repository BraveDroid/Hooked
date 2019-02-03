package com.bravedroid.data.local.service

import com.bravedroid.data.local.model.StoryLocal

interface LocalPersistence {
    fun saveOrUpdate(story: StoryLocal)
    fun getLastFetchInstant(storyId: String): Long
    fun getStoryLocalById(storyId: String): StoryLocal?
}
