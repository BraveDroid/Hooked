package com.bravedroid.presentation.feature.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.presentation.base.BaseViewModelObservable
import com.bravedroid.usecases.reader.Reader

class ReaderScreenVM(private val reader: Reader) : BaseViewModelObservable() {
     val messagesList = reader.getMessagesForStory("scavengerhunt")

    class ViewModelFactory(private val reader: Reader) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReaderScreenVM(reader) as T
        }
    }
}
