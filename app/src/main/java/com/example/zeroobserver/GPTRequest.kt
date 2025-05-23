package com.example.zeroobserver

// GPTRequest.kt
data class GPTMessage(val role: String, val content: String)

data class GPTRequest(
    val model: String = "gpt-4",
    val messages: List<GPTMessage>,
    val temperature: Double = 0.7
)

data class GPTResponse(
    val choices: List<Choice>
)


data class Choice(
    val message: GPTMessage
)

