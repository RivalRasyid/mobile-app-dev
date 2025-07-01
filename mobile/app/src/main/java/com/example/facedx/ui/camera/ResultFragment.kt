package com.example.facedx.ui.camera

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.facedx.database.SkinType
import com.example.facedx.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val args: ResultFragmentArgs by navArgs()

    private var skinType: SkinType? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentResultBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageResult.setImageURI(Uri.parse(args.imageUri))

        val predictedLabel = args.resultText.substringBefore(":").trim()
        skinType = SkinType.fromLabel(predictedLabel)

        if (skinType != null) {
            binding.textType.text = skinType!!.title
            binding.textDescription.setText(skinType!!.descRes)
        } else {
            binding.textType.text = args.resultText
            binding.textDescription.text = "Jenis kulit tidak dikenali"
        }

        binding.btRecommendation.setOnClickListener {
            skinType?.let {
                val action = ResultFragmentDirections
                    .actionResultFragmentToSaranFragment(it)
                findNavController().navigate(action)
            } ?: Toast.makeText(requireContext(), "Tidak dapat menampilkan saran.", Toast.LENGTH_SHORT).show()
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToCameraFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
