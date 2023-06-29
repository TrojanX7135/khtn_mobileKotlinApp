package com.example.finalexamandroidkotlin.adapter.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.model.Species

class SpeciesAdapter(
    private var context: Context,
    private var list: MutableList<Species>,
    private var clickItem: ClickItem

): RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    class SpeciesViewHolder(view: View): RecyclerView.ViewHolder(view)
    interface ClickItem {
        fun chooseItem(species: Species)
    }

    fun setList(list: MutableList<Species>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder =
        SpeciesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_species, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.itemView.apply {
            val textItem: TextView = this.findViewById(R.id.textSpecies)
            val containerLayout: LinearLayout = this.findViewById(R.id.containerItemSpecies)
            val species: Species = list[position] ?: return@apply

            textItem.text = species.name

            containerLayout.setOnClickListener { clickItem.chooseItem(species) }
        }
    }
}