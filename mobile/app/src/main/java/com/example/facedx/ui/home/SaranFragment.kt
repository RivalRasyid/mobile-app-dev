package com.example.facedx.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.facedx.R
import com.example.facedx.databinding.FragmentSaranBinding


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
        val nav = findNavController()


        binding.judulSaran.text = skinType.title
        binding.deskripsiSaran.text = getString(skinType.ringkasanRes)
        binding.isiCara.text = getString(skinType.perawatanRes)

        binding.btnRiwayat.setOnClickListener {
            val popped = nav.popBackStack(R.id.navigation_home, false)
            if (!popped) nav.navigate(R.id.navigation_home)
        }

        binding.btnKembali.setOnClickListener {
            val popped = nav.popBackStack(R.id.navigation_camera, false)
            if (!popped) nav.navigate(R.id.navigation_camera)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
