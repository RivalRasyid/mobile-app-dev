// ui/home/HistoryAdapter.kt
package com.example.facedx.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facedx.database.HistoryEntity
import com.example.facedx.databinding.CardHistoryBinding

class HistoryAdapter(
    private val onClick: (HistoryEntity) -> Unit
) : ListAdapter<HistoryEntity, HistoryAdapter.HistoryVH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryVH(
            CardHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: HistoryVH, position: Int) =
        holder.bind(getItem(position))

    inner class HistoryVH(private val b: CardHistoryBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(item: HistoryEntity) {
            b.cardTitle.text = item.skinTitle
            b.cardDate.text  = DateFormatter.format(item.timestamp)
            b.cardDesc.text  = item.skinDesc

            Glide.with(b.root.context)
                .load(Uri.parse(item.imageUri))
                .into(b.cardImage)

            b.root.setOnClickListener { onClick(item) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(o: HistoryEntity, n: HistoryEntity) = o.id == n.id
            override fun areContentsTheSame(o: HistoryEntity, n: HistoryEntity) = o == n
        }
    }
}
