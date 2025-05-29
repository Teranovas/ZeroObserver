package com.example.zeroobserver

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryDetailScreen : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var inputMessage: EditText
    private lateinit var btnSend: Button
    private lateinit var loadingIndicator: ProgressBar

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail_screen)

        // UI 연결
        val countryName = intent.getStringExtra("countryName") ?: "Unknown"
        findViewById<TextView>(R.id.text_country_title).text = "Country: $countryName"

        chatContainer = findViewById(R.id.chat_container)
        inputMessage = findViewById(R.id.input_message)
        btnSend = findViewById(R.id.btn_send)
        loadingIndicator = findViewById(R.id.loading_spinner)

        // ViewModel 상태 관찰
        observeViewModel()

        // 전송 버튼 클릭 시
        btnSend.setOnClickListener {
            val userMessage = inputMessage.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                inputMessage.text.clear()
                viewModel.sendMessage(userMessage) { report ->
                    // 5턴 후 보고서 생성 시 화면 이동
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
                chatContainer.removeAllViews()
                messages.forEach { addChatBubble(it) }
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                loadingIndicator.visibility = if (isLoading) ProgressBar.VISIBLE else ProgressBar.GONE
            }
        }
    }

    private fun addChatBubble(text: String) {
        val bubble = TextView(this).apply {
            this.text = text
            setPadding(16, 8, 16, 8)
            textSize = 16f
        }
        chatContainer.addView(bubble)
    }
}
