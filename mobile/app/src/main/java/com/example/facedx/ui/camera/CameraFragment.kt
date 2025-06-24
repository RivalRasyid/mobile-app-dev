package com.example.facedx.ui.camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.facedx.R
import com.example.facedx.databinding.FragmentCameraBinding
import com.yalantis.ucrop.UCrop
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null
    private var previousImageUri: Uri? = null
    private lateinit var cameraTempFile: File

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(requireContext(), "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            previousImageUri = currentImageUri
            currentImageUri = it
            cropImage(it)
        } ?: Log.d("Photo Picker", "No media selected")
    }

    private val launcherCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            currentImageUri?.let {
                cropImage(it)
            } ?: run {
                Log.e("Camera", "currentImageUri is null")
                showToast("Gagal mengambil gambar")
            }
        } else {
            Log.d("Camera", "User cancelled camera or error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvUnggahFoto.setOnClickListener {
            startGallery()
        }

        binding.cvAmbilFoto.setOnClickListener {
            if (requireContext().checkSelfPermission(android.Manifest.permission.CAMERA) ==
                android.content.pm.PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun startCamera() {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "JPEG_${timeStamp}.jpg"
        cameraTempFile = File(requireContext().cacheDir, fileName)

        try {
            val imageUri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                cameraTempFile
            )

            previousImageUri = currentImageUri
            currentImageUri = imageUri

            launcherCamera.launch(imageUri)
        } catch (e: Exception) {
            Log.e("Camera", "Gagal mendapatkan URI FileProvider", e)
            showToast("Terjadi kesalahan saat membuka kamera.")
        }
    }

    private fun cropImage(sourceUri: Uri) {
        val destinationUri = Uri.fromFile(File(requireContext().cacheDir, "${System.currentTimeMillis()}.jpg"))

        UCrop.of(sourceUri, destinationUri)
            .withMaxResultSize(2000, 2000)
            .start(requireContext(), this)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            val resultUri = UCrop.getOutput(data!!)
            resultUri?.let {
                currentImageUri = it
                val bundle = Bundle().apply {
                    putString("image_uri", it.toString())
                }
                findNavController().navigate(R.id.action_cameraFragment_to_previewFragment, bundle)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            Log.e("UCrop", "Crop error: $cropError")
            showToast(getString(R.string.error_crop_image))
        } else if (resultCode == AppCompatActivity.RESULT_CANCELED && requestCode == UCrop.REQUEST_CROP) {
            currentImageUri = previousImageUri
            showToast(getString(R.string.cancel_crop_image))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
