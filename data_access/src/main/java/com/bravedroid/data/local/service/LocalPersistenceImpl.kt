package com.bravedroid.data.local.service

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import com.bravedroid.data.local.model.StoryLocal
import com.google.gson.Gson
import java.time.Instant

class LocalPersistenceImpl(context: Context) : LocalPersistence {


    var defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @TargetApi(Build.VERSION_CODES.O)
    override fun saveOrUpdate(story: StoryLocal) {
        defaultSharedPreferences.edit().putString(story.storyId, story.toJson()).apply()
        defaultSharedPreferences.edit().putLong("${story.storyId}-INSTANT", Instant.now().toEpochMilli()).apply()
    }

    override fun getStoryLocalById(storyId: String): StoryLocal? {
        val storyLocalJsonSignified = defaultSharedPreferences.getString("$storyId-app", null) ?: return null
        return Gson().fromJson(storyLocalJsonSignified, StoryLocal::class.java)
    }

    override fun getLastFetchInstant(storyId: String): Long =
        defaultSharedPreferences.getLong("$storyId-app-INSTANT", -1)

}
