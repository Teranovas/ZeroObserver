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