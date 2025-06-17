package com.example.zeroobserver

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LeaderPersonalityActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_personality)

        radioGroup = findViewById(R.id.personality_radio_group)
        btnConfirm = findViewById(R.id.btn_confirm_personality)

        btnConfirm.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            val personality = when (selectedId) {
                R.id.radio_peaceful -> "peaceful"
                R.id.radio_aggressive -> "aggressive"
                R.id.radio_cunning -> "strategic"
                else -> "neutral"
            }

            val intent = Intent(this, CountryDetailScreen::class.java)
            intent.putExtra("leaderPersonality", personality)
            intent.putExtra("countryName", "USA") // ← 이후 확장 가능
            startActivity(intent)
            finish()
        }
    }
}
