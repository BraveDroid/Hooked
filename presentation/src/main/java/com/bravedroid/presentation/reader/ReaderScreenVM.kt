package com.bravedroid.presentation.reader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.domain.Message
import com.bravedroid.presentation.base.BaseViewModelObservable
import com.bravedroid.usecases.reader.Reader

class ReaderScreenVM(private val reader: Reader) : BaseViewModelObservable() {
    /*   val messagesList = mutableListOf<Message>()
       init{
           messagesList.add(Message(1,1,"Ahmed","hello baba"))
           messagesList.add(Message(2,2,"James","manich bouk"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(2,2,"James","manich bouk"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(2,2,"James","manich bouk"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(2,2,"James","manich bouk"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(2,2,"James","manich bouk"))
           messagesList.add(Message(3,2,"James","chkoun omek ?"))
           messagesList.add(Message(4,1,"Ahmed","lol"))
           messagesList.add(Message(5,1,"Ahmed","t7eb nfadlkou?"))
       }
   */

    val messagesList = MutableLiveData<MutableList<Message>>()

    init {
        messagesList.value = mutableListOf(

                Message(1, 1, "Rami", "hello babababababa babababa babababababa babababababababa babababababa babababababababa babababababababa mala 7ala"),
                Message(2, 2, "Ramzi", "manich bouk"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(2, 2, "James", "manich bouk"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(2, 2, "James", "manich bouk"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(2, 2, "James", "manich bouk"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(2, 2, "James", "manich bouk"),
                Message(3, 2, "James", "chkoun omek ?"),
                Message(4, 1, "Ahmed", "lol"),
                Message(5, 1, "Ahmed", "t7eb nfadlkou?")
        )

    }

    class ViewModelFactory(private val reader: Reader) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReaderScreenVM(reader) as T
        }
    }
}
