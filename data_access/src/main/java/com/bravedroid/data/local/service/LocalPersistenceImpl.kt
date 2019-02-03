package com.bravedroid.data.local.service

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import com.bravedroid.data.local.model.StoryLocal
import com.google.gson.Gson
import java.time.Instant
import java.util.*

class LocalPersistenceImpl(context: Context) : LocalPersistence {

    private var defaultSharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveOrUpdate(story: StoryLocal) {
        defaultSharedPreferences.edit().putString(story.storyId, story.toJson()).apply()
        val now: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.now().toEpochMilli()
        } else {
            Date().time
        }

        defaultSharedPreferences.edit().putLong("${story.storyId}-INSTANT", now).apply()
    }

    override fun getLastFetchInstant(storyId: String): Long =
        defaultSharedPreferences.getLong("$storyId-app-INSTANT", -1)

    override fun getStoryLocalById(storyId: String): StoryLocal? {
        val storyLocalJsonSignified = defaultSharedPreferences.getString("$storyId-app", null) ?: return null
        return Gson().fromJson(storyLocalJsonSignified, StoryLocal::class.java)
    }

}
