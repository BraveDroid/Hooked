package com.bravedroid.data.util

import com.bravedroid.data.local.model.StoryLocal
import com.bravedroid.domain.Message
import com.bravedroid.data.network.model.StoryDto
import com.bravedroid.domain.Story

fun transformToStory(dto: StoryDto): Story =
    Story(dto.uid, dto.seriesTitle, dto.storyDescription, dto.coverImageFile.url)

fun transformToStory(local: StoryLocal): Story =
    Story(local.storyId, local.title, local.description, local.urlImageCover)

fun transformToMessages(dto: StoryDto): List<Message> {
    val list = mutableListOf<Message>()
    dto.messages.forEach { messageDto ->
        list += transformToMessage(messageDto)
    }
    return list
}

private fun transformToMessage(message: com.bravedroid.data.network.model.Message): Message {
    val id = message.ordinalInStory
    val senderId = message.sender.objectId
    val senderName = message.sender.name
    val text = message.text
    return Message(id, senderId, senderName, text)
}
