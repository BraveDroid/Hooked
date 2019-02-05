package com.bravedroid.domain.model

data class Message(
    val id: Int,
    val senderId: String,
    val senderName: String,
    val text: String
)
