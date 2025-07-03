package com.example.facedx.ui.chatbot

import com.google.gson.annotations.SerializedName

data class OverrideConfig(
    @SerializedName("systemMessagePrompt")
    val systemMessagePrompt: String
)

data class ChatRequest(
    val question: String,
    val sessionId: String? = null,
    val overrideConfig: OverrideConfig? = null
)

data class ChatResponse(
    val text: String,
    val question: String?,
    val chatId: String?,
    val chatMessageId: String?,
    val isStreamValid: Boolean?,
    val sessionId: String?,
    val memoryType: String?
)
