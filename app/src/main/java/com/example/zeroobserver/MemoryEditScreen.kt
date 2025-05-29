package com.example.zeroobserver

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoryEditScreen : AppCompatActivity() {

    private lateinit var memoryList: ListView
    private lateinit var inputEdit: EditText
    private lateinit var btnSave: Button

    private var memoryLogs = mutableListOf(
        "Leader: We must secure the region.",
        "Leader: Peace talks have failed.",
        "Leader: I will not surrender."
    )

    private var selectedIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_edit_screen)

        memoryList = findViewById(R.id.list_memory)
        inputEdit = findViewById(R.id.input_memory_edit)
        btnSave = findViewById(R.id.btn_save_memory)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, memoryLogs)
        memoryList.adapter = adapter

        memoryList.setOnItemClickListener { _, _, position, _ ->
            selectedIndex = position
            inputEdit.setText(memoryLogs[position])
            inputEdit.visibility = View.VISIBLE
            btnSave.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener {
            val newText = inputEdit.text.toString()
            if (selectedIndex >= 0 && newText.isNotBlank()) {
                memoryLogs[selectedIndex] = newText
                adapter.notifyDataSetChanged()
                sendMemoryToGPT(newText)
                MemoryStorage.save(this@MemoryEditScreen, newText)  // 저장 추가
                inputEdit.text.clear()
                inputEdit.visibility = View.GONE
                btnSave.visibility = View.GONE
            }
        }
    }

    private fun sendMemoryToGPT(editedMessage: String) {
        lifecycleScope.launch {
            try {
                val request = GPTRequest(
                    messages = listOf(
                        GPTMessage("system", "You are the same leader, but your memory has been altered."),
                        GPTMessage("user", editedMessage)
                    )
                )
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getGPTResponse(request)
                }
                val reply = response.choices.firstOrNull()?.message?.content ?: "No reaction"
                Toast.makeText(this@MemoryEditScreen, "GPT: $reply", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MemoryEditScreen, "GPT 응답 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
