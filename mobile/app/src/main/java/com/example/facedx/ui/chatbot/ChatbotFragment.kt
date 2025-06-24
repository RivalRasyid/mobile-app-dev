package com.example.facedx.ui.chatbot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.facedx.databinding.FragmentChatbotBinding


class ChatbotFragment : Fragment() {

    private var _binding: FragmentChatbotBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentChatbotBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btStartBot.setOnClickListener {
            startActivity(Intent(requireContext(), ChatbotActivity::class.java))
        }

        return root
    }

}