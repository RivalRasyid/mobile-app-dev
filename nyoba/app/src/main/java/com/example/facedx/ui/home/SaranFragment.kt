package com.example.facedx.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.facedx.MainActivity
import com.example.facedx.R
import com.example.facedx.databinding.FragmentSaranBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SaranFragment : Fragment() {

    private var _binding: FragmentSaranBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentSaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SaranFragmentArgs by navArgs()
        val skinType = args.jenisKulit

        binding.judulSaran.text = skinType.title
        binding.deskripsiSaran.text = getString(skinType.ringkasanRes)
        binding.isiCara.text = getString(skinType.perawatanRes)

        binding.btnRiwayat.setOnClickListener {
            findNavController().navigate(R.id.action_saranFragment_to_homeFragment)
        }
        binding.btnKembali.setOnClickListener {
            (requireActivity() as MainActivity)
                .findViewById<BottomNavigationView>(R.id.nav_view)
                .selectedItemId = R.id.navigation_camera
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
