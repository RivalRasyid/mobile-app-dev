package com.example.facedx.ui.camera

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.facedx.R

class PreviewFragment : Fragment() {

    private lateinit var imagePreview: ImageView
    private lateinit var btConfirm: AppCompatButton
    private lateinit var btnCancel: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imagePreview = view.findViewById(R.id.imagePreview)
        btConfirm = view.findViewById(R.id.btConfirm)
        btnCancel = view.findViewById(R.id.btnCancel)

        val imageUriString = arguments?.getString("imageUri")
        val imageUri = imageUriString?.let { Uri.parse(it) }

        if (imageUri != null) {
            imagePreview.setImageURI(imageUri)
        } else {
            Toast.makeText(requireContext(), "Gambar tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        btConfirm.setOnClickListener {
            val imageUri = arguments?.getString("image_uri")
            if (imageUri != null) {
                val bundle = Bundle().apply {
                    putString("image_uri", imageUri)
                }
                findNavController().navigate(R.id.action_previewFragment_to_resultFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "Gambar tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
