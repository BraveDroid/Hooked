package com.bravedroid.data.local.service

import com.bravedroid.data.local.model.StoryStruct

interface LocalPersistence {
    fun saveOrUpdate(story: StoryStruct)
    fun getById(storyId:String):StoryStruct?
}
