package com.bravedroid.domain

data class Message(
    val id: Int,
    val senderId: String,
    val senderName: String,
    val text: String
)
