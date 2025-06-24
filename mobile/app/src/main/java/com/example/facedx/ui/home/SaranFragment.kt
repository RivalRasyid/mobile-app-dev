package com.example.facedx.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.facedx.R
import com.example.facedx.databinding.FragmentSaranBinding


class SaranFragment : Fragment() {

    private var _binding: FragmentSaranBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentSaranBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnRiwayat.setOnClickListener {
            findNavController().navigate(R.id.action_saranFragment_to_homeFragment)
        }
        binding.btnKembali.setOnClickListener {
            findNavController().navigate(R.id.action_saranFragment_to_cameraFragment)
        }

        return root
    }
}