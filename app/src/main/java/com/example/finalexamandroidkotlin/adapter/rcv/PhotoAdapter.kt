package com.example.finalexamandroidkotlin.adapter.rcv

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.model.Photo

class PhotoAdapter (
    private var context: Context,
    private var list: MutableList<Photo>,
    private var clickItem: ClickItem
): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    class PhotoViewHolder(view: View): RecyclerView.ViewHolder(view)

    interface ClickItem {
        fun chooseItem(photo: Photo)
        fun clearItem(photo: Photo)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(lsit: MutableList<Photo>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.itemView.apply {
            val imageView: ImageView = holder.itemView.findViewById(R.id.imageItemPhoto)
            val textType: TextView = holder.itemView.findViewById(R.id.textItemPhoto)
            val container: ConstraintLayout = holder.itemView.findViewById(R.id.containerItem)
            val imageClear: ImageView = holder.itemView.findViewById(R.id.imageClear)

            val photo: Photo = list[position]
            if (photo == null) return@apply

            Log.d("CheckURI", photo.uri!!.toString())

            val base64String = "${photo.uri}"
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

            imageView.setImageBitmap(bitmap)
            textType.text = photo.tag

            container.setOnClickListener{clickItem.chooseItem(photo)}
            imageClear.setOnClickListener{clickItem.clearItem(photo)}
        }
    }
}