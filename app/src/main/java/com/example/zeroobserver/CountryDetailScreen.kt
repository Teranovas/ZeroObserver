package com.example.zeroobserver

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryDetailScreen : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var inputMessage: EditText
    private lateinit var btnSend: Button
    private lateinit var loadingIndicator: ProgressBar


    private lateinit var chatRecycler: RecyclerView
    private lateinit var adapter: ChatAdapter

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail_screen)

        val countryName = intent.getStringExtra("countryName") ?: "Unknown"
        findViewById<TextView>(R.id.text_country_title).text =
            getString(R.string.country_title, countryName)

        chatRecycler = findViewById(R.id.chat_recycler)
        adapter = ChatAdapter(viewModel.chatMessages.value)
        chatRecycler.adapter = adapter

        inputMessage = findViewById(R.id.input_message)
        btnSend = findViewById(R.id.btn_send)
        loadingIndicator = findViewById(R.id.loading_spinner)

        viewModel.setMemory(this)  // 🔥 메모리 반영

        viewModel.loadProgress(this, countryName)

        observeViewModel()



        btnSend.setOnClickListener {
            val userMessage = inputMessage.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                inputMessage.text.clear()
                viewModel.sendMessage(userMessage) { report ->
                    val intent = Intent(this, EndingReportActivity::class.java)
                    intent.putExtra("reportResult", report)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.chatMessages.collectLatest { messages ->
                adapter = ChatAdapter(messages)
                chatRecycler.adapter = adapter
                chatRecycler.scrollToPosition(messages.size - 1)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                loadingIndicator.visibility =
                    if (isLoading) ProgressBar.VISIBLE else ProgressBar.GONE
            }
        }
    }

    private fun addChatBubble(text: String) {
        val isUser = text.startsWith("You:")

        val bubbleView = layoutInflater.inflate(R.layout.item_chat_bubble, chatContainer, false)
        val bubbleContainer = bubbleView.findViewById<LinearLayout>(R.id.bubble_container)
        val messageView = bubbleView.findViewById<TextView>(R.id.text_message)

        messageView.text = text.removePrefix("You:").removePrefix("Leader:").trim()
        messageView.background = if (isUser) {
            getDrawable(R.drawable.bg_bubble_user)
        } else {
            getDrawable(R.drawable.bg_bubble_gpt)
        }

        bubbleContainer.gravity = if (isUser) Gravity.END else Gravity.START
        chatContainer.addView(bubbleView)
    }
}
