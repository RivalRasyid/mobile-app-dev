package com.example.facedx.ui.chatbot

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.facedx.R
import com.example.facedx.databinding.ActivityChatbotBinding
import com.example.facedx.ui.camera.CameraFragment

class ChatbotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatbotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackDT.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivCamera.setOnClickListener {
            val fragment = CameraFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

            binding.fragmentContainer.visibility = View.VISIBLE

            binding.scrollView.visibility = View.GONE
            binding.chatInputLayout.visibility = View.GONE
            binding.header.visibility = View.GONE
        }

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                Toast.makeText(this, "Kirim: $message", Toast.LENGTH_SHORT).show()
                binding.etMessage.text.clear()
            } else {
                Toast.makeText(this, "Pesan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
