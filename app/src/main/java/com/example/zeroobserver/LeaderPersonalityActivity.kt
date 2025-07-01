package com.example.zeroobserver

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LeaderPersonalityActivity : AppCompatActivity() {
    private lateinit var viewModel: LeaderReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_personality)

        viewModel = ViewModelProvider(this)[LeaderReportViewModel::class.java]

        val country = intent.getStringExtra("country") ?: "Unknown"

        val btn = findViewById<Button>(R.id.btn_generate_report)
        val reportView = findViewById<TextView>(R.id.tv_leader_report)

        btn.setOnClickListener {
            viewModel.generateLeaderReport(country)
        }

        viewModel.reportText.observe(this) {
            reportView.text = it
        }
    }
}

