package com.example.facedx.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.R
import com.example.facedx.database.SaranEntity
import com.example.facedx.databinding.CardFaceConditionBinding

class SaranAdapter(
    private val list: List<SaranEntity>,
    private val onItemClick: (SaranEntity) -> Unit
) : RecyclerView.Adapter<SaranAdapter.SaranViewHolder>() {

    private val imageMap = mapOf(
        "Kulit Sehat" to R.drawable.kulit_sehat,
        "Kulit Berminyak" to R.drawable.kulit_minyak,
        "Kulit Kering" to R.drawable.kulit_kering
    )

    inner class SaranViewHolder(val binding: CardFaceConditionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SaranEntity) {
            val jenisKulit = item.jenisKulit ?: "-"
            binding.cardTitle.text = jenisKulit

            val imageRes = imageMap[jenisKulit] ?: R.drawable.kulit_sehat
            binding.cardImage.setImageResource(imageRes)

            binding.root.setOnClickListener {
                if (item.jenisKulit != null) {
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaranViewHolder {
        val binding = CardFaceConditionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaranViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SaranViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
