package com.example.zeroobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MemoryEditScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_edit_screen)

        val memoryList = findViewById<ListView>(R.id.list_memory)
        val dummyData =
            listOf("Leader changed speech", "Crisis reinterpreted", "Public opinion reversed")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dummyData)
        memoryList.adapter = adapter
    }
}