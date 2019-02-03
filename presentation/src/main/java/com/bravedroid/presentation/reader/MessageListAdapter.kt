package com.bravedroid.presentation.reader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.domain.Message
import com.bravedroid.presentation.databinding.LayoutMessageItemBinding

class MessageListAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<MessageListAdapter.BindingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding: LayoutMessageItemBinding = LayoutMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lastIndex=20
        return BindingHolder(binding);
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item: Message = messageList[position]
        holder.binding.message = item
        holder.binding.executePendingBindings()
    }

    class BindingHolder(val binding: LayoutMessageItemBinding) : RecyclerView.ViewHolder(binding.root)

}
