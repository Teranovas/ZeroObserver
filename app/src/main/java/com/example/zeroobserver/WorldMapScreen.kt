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
        val mapImage = findViewById<ImageView>(R.id.image_world_map)

        btnEditMemory.setOnClickListener {
            startActivity(Intent(this, MemoryEditScreen::class.java))
        }

        btnDashboard.setOnClickListener {
            startActivity(Intent(this, GlobalCrisisDashboard::class.java))
        }

        mapImage.setOnClickListener {
            startActivity(Intent(this, CountryDetailScreen::class.java))
        }
    }
}