package com.bravedroid.data.util

import com.bravedroid.data.local.model.StoryStruct
import com.bravedroid.domain.Message
import com.bravedroid.data.network.model.StoryDto
import com.bravedroid.domain.Story

fun transformToStory(dto: StoryDto): Story =
    Story(dto.uid, dto.seriesTitle, dto.storyDescription, dto.coverImageFile.url)

fun transformToStory(struct: StoryStruct): Story =
    Story(struct.storyId, struct.title, struct.description, struct.urlImageCover)

fun transformToMessages(dto: StoryDto): List<Message> {
    val list = mutableListOf<Message>()
    dto.messages.forEach { messageDto ->
        list += toMessage(messageDto)
    }
    return list
}

private fun toMessage(message: com.bravedroid.data.network.model.Message): Message {
    val id = message.ordinalInStory
    val senderId = message.sender.objectId
    val senderName = message.sender.name
    val text = message.text
    return Message(id, senderId, senderName, text)
}
