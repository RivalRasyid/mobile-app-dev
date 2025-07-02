package com.example.facedx.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facedx.database.SkinType
import com.example.facedx.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val historyVm: HistoryViewModel by viewModels()

    private lateinit var faceConditionAdapter: FaceConditionAdapter
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerViewKulit()
        setupHistoryRecyclerView()
        observeHistory()
        return binding.root
    }

    private fun setupRecyclerViewKulit() {
        val list = listOf(
            SkinType.SEHAT,
            SkinType.BERMINYAK,
            SkinType.KERING
        )
        faceConditionAdapter = FaceConditionAdapter(list) { skinType ->
            val action = HomeFragmentDirections
                .actionHomeFragmentToSaranFragment(skinType)
            findNavController().navigate(action)
        }

        binding.rvJenisKulit.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = faceConditionAdapter
        }
    }

    private fun setupHistoryRecyclerView() {
        historyAdapter = HistoryAdapter { entity ->
            val skinType = SkinType.fromLabel(entity.skinTitle?.trim() ?: "")
            skinType?.let {
                val action = HomeFragmentDirections
                    .actionHomeFragmentToSaranFragment(it)
                findNavController().navigate(action)
            } ?: Toast.makeText(requireContext(),
                "Jenis kulit tidak dikenali", Toast.LENGTH_SHORT).show()
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    private fun observeHistory() {
        historyVm.histories.observe(viewLifecycleOwner) { list ->
            historyAdapter.submitList(list)
            binding.emptyStateTextView.visibility =
                if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
