package com.bravedroid.data.local.model

import com.bravedroid.domain.Message
import com.google.gson.Gson

data class StoryStruct(
    val storyId: String,
    val title: String,
    val description: String?,
    val urlImageCover: String,
    val messages: List<Message>
) {
    fun toJson() = Gson().toJson(this )

}
