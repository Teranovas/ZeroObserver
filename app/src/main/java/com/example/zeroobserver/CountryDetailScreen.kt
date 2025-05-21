package com.example.zeroobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryDetailScreen : AppCompatActivity() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var inputMessage: EditText
    private lateinit var btnSend: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail_screen)

        val countryName = intent.getStringExtra("countryName") ?: "Unknown"
        findViewById<TextView>(R.id.text_country_title).text = "Country: $countryName"

        chatContainer = findViewById(R.id.chat_container)
        inputMessage = findViewById(R.id.input_message)
        btnSend = findViewById(R.id.btn_send)

        btnSend.setOnClickListener {
            val userMessage = inputMessage.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addChatBubble("You: $userMessage")
                inputMessage.text.clear()
                sendToGPT(userMessage)
            }
        }
    }
    private fun addChatBubble(text: String) {
        val bubble = TextView(this)
        bubble.text = text
        bubble.setPadding(16, 8, 16, 8)
        bubble.textSize = 16f
        chatContainer.addView(bubble)
    }

    private fun sendToGPT(message: String) {
        lifecycleScope.launch {
            try {
                val request = GPTRequest(
                    messages = listOf(
                        GPTMessage("system", "You are the leader of the nation."),
                        GPTMessage("user", message)
                    )
                )
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getGPTResponse(request)
                }
                val reply = response.choices.firstOrNull()?.message?.content ?: "No response"
                addChatBubble("Leader: $reply")
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@CountryDetailScreen, "GPT 응답 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}