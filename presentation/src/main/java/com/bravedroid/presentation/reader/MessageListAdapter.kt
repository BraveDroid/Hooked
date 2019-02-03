package com.bravedroid.presentation.reader

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.domain.Message
import com.bravedroid.presentation.databinding.LayoutMessageItemBinding

class MessageListAdapter(private val messageList: List<Message>) :
    RecyclerView.Adapter<MessageListAdapter.BindingHolder>() {

    var lastIndex: Int = 1

    fun updateLastIndex() {
        lastIndex++
        notifyDataSetChanged()
        Log.v("*lastIndex****", "$lastIndex")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding: LayoutMessageItemBinding =
            LayoutMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lastIndex = lastIndex
        return BindingHolder(binding);
    }

    override fun getItemCount(): Int = lastIndex

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item: Message = messageList[position]
        holder.binding.message = item
        holder.binding.messageItem.visibility = if (item.id > lastIndex) View.GONE else View.VISIBLE
        holder.binding.executePendingBindings()
    }

    class BindingHolder(val binding: LayoutMessageItemBinding) : RecyclerView.ViewHolder(binding.root)

}
