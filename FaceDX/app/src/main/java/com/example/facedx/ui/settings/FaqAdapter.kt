package com.example.facedx.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.R

class FaqAdapter(private val faqList: List<FaqItem>) :
    RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    inner class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val answerText: TextView = itemView.findViewById(R.id.answerText)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrowIcon)

        init {
            itemView.setOnClickListener {
                val item = faqList[adapterPosition]
                item.isExpanded = !item.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val item = faqList[position]
        holder.questionText.text = item.question
        holder.answerText.text = item.answer

        if (item.isExpanded) {
            holder.answerText.visibility = View.VISIBLE
            holder.arrowIcon.animate().rotation(180f).setDuration(300).start()
        } else {
            holder.answerText.visibility = View.GONE
            holder.arrowIcon.animate().rotation(0f).setDuration(300).start()
        }
    }

    override fun getItemCount() = faqList.size
}
