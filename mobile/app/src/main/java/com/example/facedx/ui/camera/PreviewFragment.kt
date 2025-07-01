package com.example.facedx.ui.camera

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facedx.databinding.FragmentPreviewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.task.vision.classifier.Classifications

class PreviewFragment : Fragment(), ImageClassifierHelper.ClassifierListener {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageUri: Uri
    private lateinit var classifier: ImageClassifierHelper
    private val loadingDialog by lazy { ProgressDialog(requireContext()).apply {
        setMessage("Menganalisis…"); setCancelable(false) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentPreviewBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageUri = Uri.parse(requireArguments().getString("image_uri"))
        binding.imagePreview.setImageURI(imageUri)

        classifier = ImageClassifierHelper(
            context = requireContext(),
            classifierListener = this
        )

        binding.btConfirm.setOnClickListener { startClassification() }
        binding.btnCancel.setOnClickListener { findNavController().navigateUp() }
    }

    private fun startClassification() {
        loadingDialog.show()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Default) {
            classifier.classifyStaticImage(imageUri)
        }
    }

    override fun onResults(result: List<Classifications>?) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            loadingDialog.dismiss()

            if (result.isNullOrEmpty() || result[0].categories.isEmpty()) {
                showToast("Tak ada hasil klasifikasi.");  return@launch
            }

            val best = result[0].categories[0]
            val label = best.label
            val conf  = best.score
            val resultText = "$label : ${(conf * 100).toInt()}%"

            val action = PreviewFragmentDirections
                .actionPreviewFragmentToResultFragment(
                    imageUri.toString(),
                    resultText,
                    conf
                )
            findNavController().navigate(action)
        }
    }

    override fun onError(error: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            loadingDialog.dismiss()
            showToast("Error: $error")
        }
    }

    private fun showToast(msg: String) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
