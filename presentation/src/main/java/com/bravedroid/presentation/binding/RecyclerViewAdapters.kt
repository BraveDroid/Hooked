package com.bravedroid.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.domain.Message
import com.bravedroid.presentation.R
import com.bravedroid.presentation.reader.MessageListAdapter


@BindingAdapter("messageList")
fun RecyclerView.setMessageList(messageList: List<Message>?) {
    if (messageList == null) return
    layoutManager = LinearLayoutManager(context)

    addItemDecoration(ItemOffsetDecoration(context, R.dimen.item_offset))

    adapter = MessageListAdapter(messageList)
}
