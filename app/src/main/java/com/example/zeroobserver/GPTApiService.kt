package com.example.zeroobserver

interface GPTApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getGPTResponse(
        @Body request: GPTRequest
    ): GPTResponse
}