package com.example.tryagain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    private var dataList: List<DataModel>,
    private val onItemClick: (DataModel) -> Unit

) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var filteredList: List<DataModel> = dataList
    private var onItemClickListener: ((DataModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(data: DataModel) {
            nameTextView.text = data.name
        }
    }

    fun filterList(filteredList: List<DataModel>) {
        this.filteredList = filteredList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (DataModel) -> Unit) {
        onItemClickListener = listener
    }
}
