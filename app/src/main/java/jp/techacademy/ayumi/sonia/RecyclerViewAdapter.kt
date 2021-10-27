package jp.techacademy.ayumi.sonia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val videIds: MutableList<String>, private val titles: MutableList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private lateinit var listener: OnClickListener

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val titleView: TextView = view.findViewById(R.id.view_title)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = titles[position]
        // セルのタップイベントをセット
        holder.itemView.setOnClickListener {
            listener.onItemClick(videIds[position])
        }
    }

    override fun getItemCount(): Int {
        return videIds.size
    }

    interface OnClickListener {
        fun onItemClick(data: String)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}
