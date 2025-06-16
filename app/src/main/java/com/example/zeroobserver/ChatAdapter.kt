package com.example.zeroobserver

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<String>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bubbleContainer: LinearLayout = view.findViewById(R.id.bubble_container)
        val textMessage: TextView = view.findViewById(R.id.text_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_bubble, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val text = messages[position]
        val isUser = text.startsWith("You:")

        holder.textMessage.text = text.removePrefix("You:").removePrefix("Leader:").trim()
        holder.textMessage.background = holder.itemView.context.getDrawable(
            if (isUser) R.drawable.bg_bubble_user else R.drawable.bg_bubble_gpt
        )
        holder.bubbleContainer.gravity = if (isUser) Gravity.END else Gravity.START
    }

    override fun getItemCount() = messages.size
}
