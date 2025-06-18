package com.example.zeroobserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class WorldMapScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world_map_screen)

        val btnEditMemory = findViewById<Button>(R.id.btn_edit_memory)
        val btnDashboard = findViewById<Button>(R.id.btn_global_dashboard)
        val btnContinue = findViewById<Button>(R.id.btn_continue_country)
        val btnReports = findViewById<Button>(R.id.btn_view_reports)
        val mapImage = findViewById<ImageView>(R.id.image_world_map)

        btnEditMemory.setOnClickListener {
            startActivity(Intent(this, MemoryEditScreen::class.java))
        }

        btnDashboard.setOnClickListener {
            startActivity(Intent(this, GlobalCrisisDashboard::class.java))
        }

        btnContinue.setOnClickListener {
            val intent = Intent(this, CountryDetailScreen::class.java)
            intent.putExtra("countryName", "Korea") // 예시
            intent.putExtra("leaderPersonality", "strategic") // 기본 성격
            startActivity(intent)
        }

        btnReports.setOnClickListener {
            startActivity(Intent(this, CompareReportActivity::class.java))
        }

        mapImage.setOnClickListener {
            startActivity(Intent(this, CountryDetailScreen::class.java))
            intent.putExtra("countryName", "USA") // 임시 예시
            startActivity(intent)
        }
    }
}