package com.example.zeroobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EndingReportActivity : AppCompatActivity() {
//    private lateinit var resultView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ending_report)

        val resultView = findViewById<TextView>(R.id.text_ending_result)
        val report = intent.getStringExtra("reportResult") ?: "No report"
        resultView.text = report

        val countryName = intent.getStringExtra("countryName") ?: "Unknown"
        ReportStorage.saveReport(this, countryName, report) // 🔥 저장




    }
}