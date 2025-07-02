package com.example.facedx.ui.settings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.facedx.R
import com.example.facedx.database.HistoryDatabase
import com.example.facedx.databinding.FragmentDeleteBinding
import kotlinx.coroutines.launch


class DeleteFragment : DialogFragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btConfirmDelete.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val dao = HistoryDatabase.getInstance(requireContext()).historyDao()
                try {
                    dao.clearAll()
                    dismiss()
                    findNavController().navigate(
                        R.id.action_deleteFragment_to_deletedFragment
                    )
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Gagal Menghapus Riwayat",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val nav = findNavController()

        binding.btnCancel.setOnClickListener {
            val popped = nav.popBackStack(R.id.navigation_settings, false)
            if (!popped) nav.navigate(R.id.navigation_settings)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
