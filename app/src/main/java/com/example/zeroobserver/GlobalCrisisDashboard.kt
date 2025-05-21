package com.example.zeroobserver

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*

class GlobalCrisisDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_crisis_dashboard)

        val chart = findViewById<LineChart>(R.id.chart_global_status)

        val politicalUnrest = listOf(30f, 40f, 35f, 60f, 70f)
        val autonomyLevel = listOf(80f, 75f, 70f, 65f, 60f)
        val techLevel = listOf(20f, 30f, 40f, 50f, 55f)

        val entries1 = politicalUnrest.mapIndexed { index, value -> Entry(index.toFloat(), value) }
        val entries2 = autonomyLevel.mapIndexed { index, value -> Entry(index.toFloat(), value) }
        val entries3 = techLevel.mapIndexed { index, value -> Entry(index.toFloat(), value) }

        val dataSet1 = LineDataSet(entries1, "Political Unrest").apply {
            color = Color.RED
            valueTextColor = Color.BLACK
        }

        val dataSet2 = LineDataSet(entries2, "Autonomy Level").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
        }

        val dataSet3 = LineDataSet(entries3, "Tech Level").apply {
            color = Color.GREEN
            valueTextColor = Color.BLACK
        }

        val lineData = LineData(dataSet1, dataSet2, dataSet3)
        chart.data = lineData

        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.description.text = "Global Crisis Status"
        chart.animateX(1500)
    }
}