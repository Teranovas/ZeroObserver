package com.example.zeroobserver

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CompareReportActivity : AppCompatActivity() {

    private lateinit var reportContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_report)

        reportContainer = findViewById(R.id.report_container)
        val reports = ReportStorage.loadAllReports(this)

        for ((country, report) in reports) {
            val card = TextView(this).apply {
                text = "🌍 $country\n\n$report"
                textSize = 16f
                setPadding(24, 16, 24, 16)
                setBackgroundResource(R.drawable.bg_report_card)
                setTextColor(resources.getColor(android.R.color.black, theme))
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 24)
            reportContainer.addView(card, params)
        }
    }
}
