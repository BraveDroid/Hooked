package com.bravedroid.data.local.service

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bravedroid.data.local.model.StoryStruct
import com.google.gson.Gson

class LocalPersistenceImpl(context: Context) : LocalPersistence {
    var defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveOrUpdate(story: StoryStruct) {
        defaultSharedPreferences.edit().putString(story.storyId, story.toJson()).apply()
    }

    override fun getById(storyId: String): StoryStruct? {
        val storyStructJsonSignified = defaultSharedPreferences.getString("$storyId-app", null) ?: return null
        return Gson().fromJson(storyStructJsonSignified, StoryStruct::class.java)
    }
}
