package com.example.facedx.ui.chatbot

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface FlowiseApi {
    @POST("api/v1/prediction/{id}")
    suspend fun sendMessage(
        @Path("id") botId: String,
        @Body body: ChatRequest
    ): ChatResponse
}
