package com.example.facedx.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.database.SkinType
import com.example.facedx.databinding.CardFaceConditionBinding

class FaceConditionAdapter(
    private val items: List<SkinType>,
    private val onClick: (SkinType) -> Unit
) : RecyclerView.Adapter<FaceConditionAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: CardFaceConditionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SkinType) = with(binding) {
            cardTitle.text = item.title
            cardImage.setImageResource(item.imageRes)
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(CardFaceConditionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}
