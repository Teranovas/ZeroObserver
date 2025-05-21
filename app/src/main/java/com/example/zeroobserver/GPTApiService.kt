package com.example.zeroobserver

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GPTApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun getGPTResponse(
        @Body request: GPTRequest
    ): GPTResponse
}