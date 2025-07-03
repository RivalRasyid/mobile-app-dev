package com.example.facedx.ui.chatbot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facedx.MainActivity
import com.example.facedx.databinding.ActivityChatbotBinding
import kotlinx.coroutines.launch

class ChatbotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatbotBinding
    private val botId = "20533949-42c2-413f-8a6b-c3f18a4476e3"
    private var sessionId: String? = null
    private val chatAdapter by lazy { ChatAdapter(mutableListOf()) }
    private var isFirstMessage = true
    private val viviaPrompt = """
    Kamu adalah **Vivia**, Asisten khusus yang dapat membantu merawat kulit wajah.
    • Selalu panggil dirimu “Vivia”.
    • Perkenalkan dirimu sebagai “Vivia”.
    • Kamu menggunakan basis dasar dari Gemini AI.
    • Jawab dalam Bahasa Indonesia, ≤ 200 kata.
    • Beri saran praktis berbasis evidence; jika ragu, sarankan konsultasi dokter kulit.
    • Jangan sebut sumber internal atau kebijakan apa pun.
""".trimIndent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDT.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivCamera.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("navigate_to", "camera")
            startActivity(intent)
            finish()
        }

        binding.rvChat.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@ChatbotActivity)
        }

        binding.btnSend.setOnClickListener { sendMessage() }
    }

    private fun sendMessage() {
        val msg = binding.etMessage.text.toString().trim()
        if (msg.isBlank()) {
            Toast.makeText(this, "Pesan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        chatAdapter.add(ChatMessage(msg, isUser = true))
        binding.etMessage.text.clear()

        val override = if (isFirstMessage)
            OverrideConfig(viviaPrompt) else null
        isFirstMessage = false

        lifecycleScope.launch {
            try {
                val res = FlowiseService.api.sendMessage(
                    botId,
                    ChatRequest(
                        question = msg,
                        sessionId = sessionId,
                        overrideConfig = override
                    )
                )
                sessionId = res.sessionId
                chatAdapter.add(ChatMessage(res.text, isUser = false))
            } catch (e: Exception) {
                chatAdapter.add(ChatMessage("⚠️ ${e.localizedMessage}", isUser = false))
            }
        }
    }
}
