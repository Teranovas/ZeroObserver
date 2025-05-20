package com.example.zeroobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class CountryDetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail_screen)

        val chatContainer = findViewById<LinearLayout>(R.id.chat_container)
        val inputMessage = findViewById<EditText>(R.id.input_message)
        val btnSend = findViewById<Button>(R.id.btn_send)

        btnSend.setOnClickListener {
            val message = inputMessage.text.toString()
            if (message.isNotBlank()) {
                val textView = TextView(this)
                textView.text = "You: $message"
                chatContainer.addView(textView)
                inputMessage.text.clear()
            }
        }
    }
}