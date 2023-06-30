package com.example.finalexamandroidkotlin.adapter.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamandroidkotlin.R

class TagAdapter(
    private var context: Context,
    private var list: List<String>
) : RecyclerView.Adapter<TagAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_tag_articles, parent, false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val textTag: TextView = holder.itemView.findViewById(R.id.textTagArticles)

            val s: String = list[position]
            textTag.text = s
        }
    }
}