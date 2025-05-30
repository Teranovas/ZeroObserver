package com.example.zeroobserver

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val gptRepository = GptRepository()

    private val _chatMessages = MutableStateFlow<List<String>>(emptyList())
    val chatMessages: StateFlow<List<String>> = _chatMessages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var turnCount = 0
    private val maxTurns = 5
    private val history = mutableListOf<String>()

    fun sendMessage(userInput: String, onFinish: (String) -> Unit) {
        val userLine = "You: $userInput"
        _chatMessages.update { it + userLine }
        history.add(userLine)

        _isLoading.value = true

        viewModelScope.launch {
            val gptReply = gptRepository.sendToGPT(
                listOf(
                    GPTMessage("system", "You are the leader of the nation."),
                    GPTMessage("user", userInput)
                )
            )

            val replyLine = "Leader: $gptReply"
            _chatMessages.update { it + replyLine }
            history.add(replyLine)

            _isLoading.value = false

            turnCount++
            if (turnCount >= maxTurns) {
                val report = gptRepository.getReportFromHistory(history)
                onFinish(report)
            }
        }
    }

    fun setMemory(context: Context) {
        val memory = MemoryStorage.load(context)
        if (memory.isNotBlank()) {
            history.add(0, "System memory: $memory")
        }
    }

    fun saveProgress(context: Context, country: String) {
        GameProgressStorage.saveProgress(context, country, history, turnCount)
    }

    fun loadProgress(context: Context, country: String) {
        val (savedHistory, savedTurn) = GameProgressStorage.loadProgress(context, country)
        history.clear()
        history.addAll(savedHistory)
        turnCount = savedTurn
        _chatMessages.value = savedHistory // 화면에도 표시
    }
}
