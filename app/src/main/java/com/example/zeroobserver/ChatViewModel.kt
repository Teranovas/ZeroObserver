package com.example.zeroobserver

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import com.example.zeroobserver.GptRepository


class ChatViewModel {

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
}