package com.example.facedx.ui.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.R

class ChatAdapter(
    private val messages: MutableList<ChatMessage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_USER = 1
        private const val VIEW_BOT  = 2
    }

    private class UserVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tv_user)
        fun bind(msg: ChatMessage) { tvMessage.text = msg.text }
    }

    private class BotVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tv_bot)
        fun bind(msg: ChatMessage) { tvMessage.text = msg.text }
    }

    override fun getItemViewType(position: Int): Int =
        if (messages[position].isUser) VIEW_USER else VIEW_BOT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_USER -> {
                val v = inflater.inflate(R.layout.item_user, parent, false)
                UserVH(v)
            }
            else -> {
                val v = inflater.inflate(R.layout.item_bot, parent, false)
                BotVH(v)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        when (holder) {
            is UserVH -> holder.bind(msg)
            is BotVH  -> holder.bind(msg)
        }
    }

    override fun getItemCount(): Int = messages.size

    fun add(msg: ChatMessage) {
        messages += msg
        notifyItemInserted(messages.lastIndex)
    }
}
