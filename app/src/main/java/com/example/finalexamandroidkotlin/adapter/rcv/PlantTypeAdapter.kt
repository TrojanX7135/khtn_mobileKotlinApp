package com.example.finalexamandroidkotlin.adapter.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.model.PlantType

class PlantTypeAdapter(
    private var context: Context,
    private var list: MutableList<PlantType>
): RecyclerView.Adapter<PlantTypeAdapter.PlantTypeViewHolder>() {
    class PlantTypeViewHolder(view: View): RecyclerView.ViewHolder(view)

    fun setList(list: MutableList<PlantType>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantTypeViewHolder =
        PlantTypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plants_type, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlantTypeViewHolder, position: Int) {
        holder.itemView.apply {
            val textCategoryName: TextView = holder.itemView.findViewById(R.id.textCategoryItemPlant)
            val textQuantity: TextView = holder.itemView.findViewById(R.id.textNumberCategoryItemPlant)

            val plantType: PlantType = list[position]
            if (plantType == null) return@apply

            textCategoryName.text = plantType.categoryName
            textQuantity.text = "${plantType.quantity} Types of Plants"
        }
    }
}