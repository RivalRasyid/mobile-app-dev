package com.example.facedx.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.R

class FaceConditionAdapter(
    private val items: List<FaceCondition>,
    private val onItemClick: (FaceCondition) -> Unit
) : RecyclerView.Adapter<FaceConditionAdapter.FaceConditionViewHolder>() {

    inner class FaceConditionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.card_title)
        val imageView: ImageView = itemView.findViewById(R.id.card_image)
        val cardView: CardView = itemView as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaceConditionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_face_condition, parent, false)
        return FaceConditionViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaceConditionViewHolder, position: Int) {
        val item = items[position]
        holder.titleText.text = item.title
        holder.imageView.setImageResource(item.imageResId)

        holder.cardView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
