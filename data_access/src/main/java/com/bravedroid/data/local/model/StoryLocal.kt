package com.bravedroid.data.local.model

import com.bravedroid.domain.model.Message
import com.google.gson.Gson

data class StoryLocal(
    val storyId: String,
    val title: String,
    val description: String?,
    val urlImageCover: String,
    val messages: List<Message>
) {
    fun toJson() = Gson().toJson(this )

}
