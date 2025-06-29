package com.example.facedx.ui.home

import HistoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facedx.R
import com.example.facedx.database.SkinType
import com.example.facedx.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerViewKulit()
        setupHistoryRecyclerView()
        return root
    }


    private lateinit var faceConditionAdapter: FaceConditionAdapter

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
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = faceConditionAdapter
        }
    }

    private fun setupHistoryRecyclerView() {
        val sampleHistory = listOf(
            HistoryItem(
                title = "Kulit Berminyak",
                date = "21 Juni 2025",
                description = "Gunakan face wash oil control & moisturizer gel",
                imageResId = R.drawable.history
            ),
            HistoryItem(
                title = "Kulit Kering",
                date = "20 Juni 2025",
                description = "Gunakan hydrating toner & pelembap berbahan ceramide",
                imageResId = R.drawable.history
            )
        )

        val adapter = HistoryAdapter(sampleHistory) { selectedItem ->
            Toast.makeText(requireContext(), "Klik: ${selectedItem.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
