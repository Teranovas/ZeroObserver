package com.example.zeroobserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start_game)
        val btnContinue = findViewById<Button>(R.id.btn_continue)
        val btnSettings = findViewById<Button>(R.id.btn_settings)

        btnStart.setOnClickListener {
            val intent = Intent(this, WorldMapScreen::class.java)
            startActivity(intent)
        }
    }
}