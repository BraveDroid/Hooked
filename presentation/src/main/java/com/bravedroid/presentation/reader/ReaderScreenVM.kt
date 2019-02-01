package com.bravedroid.presentation.reader

import androidx.databinding.BaseObservable
import com.bravedroid.domain.Message

class ReaderScreenVM : BaseObservable() {
    val messagesList = listOf<Message>()
}
