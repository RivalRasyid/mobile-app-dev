import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.facedx.R
import com.example.facedx.ui.home.HistoryItem

class HistoryAdapter(
    private val historyList: List<HistoryItem>,
    private val onItemClick: (HistoryItem) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage: ImageView = itemView.findViewById(R.id.card_image)
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val cardDate: TextView = itemView.findViewById(R.id.card_date)
        val cardDesc: TextView = itemView.findViewById(R.id.card_desc)
        val cardView: CardView = itemView as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.cardTitle.text = item.title
        holder.cardDate.text = item.date
        holder.cardDesc.text = item.description
        holder.cardImage.setImageResource(item.imageResId)

        holder.cardView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = historyList.size
}
