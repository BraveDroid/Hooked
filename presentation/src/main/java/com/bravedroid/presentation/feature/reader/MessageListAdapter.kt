package com.bravedroid.presentation.feature.reader

import android.graphics.Color
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
        holder.binding.sender.setTextColor(Color.parseColor(ColorsManager.getColor(item.senderId)))
        holder.binding.message = item
        holder.binding.messageItem.visibility = if (item.id > lastIndex) View.GONE else View.VISIBLE
        holder.binding.executePendingBindings()
    }

    class BindingHolder(val binding: LayoutMessageItemBinding) : RecyclerView.ViewHolder(binding.root)

}

object ColorsManager {
    private const val FIRST_COLOR = "#26C4B5"
    private const val SECOND_COLOR = "#b271df"

    private val senderColorMap = mutableMapOf<String, String>()

    fun getColor(senderId: String): String = senderColorMap[senderId]
            ?: if (senderColorMap.isEmpty()) {
                senderColorMap[senderId] = FIRST_COLOR
                FIRST_COLOR
            } else {
                senderColorMap[senderId] = SECOND_COLOR
                SECOND_COLOR
            }
}
