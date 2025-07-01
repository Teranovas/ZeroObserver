package com.example.zeroobserver

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GptRepository {

    suspend fun sendToGPT(messages: List<GPTMessage>): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.api.getGPTResponse(
                    GPTRequest(messages = messages)
                )
                response.choices.firstOrNull()?.message?.content ?: "No response"
            } catch (e: Exception) {
                "Error occurred"
            }
        }
    }

    suspend fun getResponse(prompt: String): String {
        // TODO: 실제 GPT API 호출을 넣어야 합니다
        // 아래는 임시 응답입니다
        return withContext(Dispatchers.IO) {
            "This is a mock response to: $prompt"
        }
    }

    suspend fun getReportFromHistory(history: List<String>): String {
        val prompt = buildString {
            append("Summarize the national situation in 3 sentences.\n")
            history.forEach { appendLine(it) }
        }

        return sendToGPT(
            listOf(
                GPTMessage("system", "You are a neutral analyst writing reports."),
                GPTMessage("user", prompt)
            )
        )
    }
}