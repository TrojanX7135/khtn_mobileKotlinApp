package com.example.finalexamandroidkotlin.adapter.rcv

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.model.Plant

class PlantAdapter(
    private var context: Context,
    private var list: MutableList<Plant>,
    private var clickItem: ClickItem
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view)
    interface ClickItem {
        fun chooseItem(plant: Plant)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<Plant>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder =
        PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.itemView.apply {
            val imageView: ImageView = this.findViewById(R.id.imageItemPlant)
            val textName: TextView = this.findViewById(R.id.textPlantName)
            val textDescription: TextView = this.findViewById(R.id.textDescriptionPlant)
            val containerItem: LinearLayout = this.findViewById(R.id.containerItemPlant)
            val plant: Plant = list[position] ?: return@apply

            if (position % 2 != 0) {
                containerItem.setBackgroundResource(R.color.color_bg_item_plant_2)
            } else {
                containerItem.setBackgroundResource(R.color.color_bg_item_plant_1)
            }

            Glide
                .with(context)
                .load(plant.image)
                .into(imageView)
            textName.text = plant.name
            textDescription.text = split(plant.description.toString())

            containerItem.setOnClickListener { clickItem.chooseItem(plant) }
        }
    }

    private fun split(input: String): String {
        return if (input.length <= 50) {
            input
        } else {
            val arr = input.split("")
            var result = ""
            for (i in 0 until 40) {
                result += arr[i]
            }

            "${result}..."
        }
    }
}