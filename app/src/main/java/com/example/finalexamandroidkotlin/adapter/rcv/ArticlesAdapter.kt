package com.example.finalexamandroidkotlin.adapter.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalexamandroidkotlin.R
import com.example.finalexamandroidkotlin.database.user_current.UserDatabase
import com.example.finalexamandroidkotlin.model.Articles
import com.example.finalexamandroidkotlin.model.UserCurrent

class ArticlesAdapter(
    private var context: Context,
    private var list: MutableList<Articles>,
    private var clickItem: ClickItem
) : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface ClickItem {
        fun clickItem(articles: Articles)
        fun handleSave(articles: Articles)
        fun handleLike(articles: Articles)
    }

    fun setList(list: MutableList<Articles>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_articles, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.itemView.apply {
            val imageItem: ImageView = holder.itemView.findViewById(R.id.imageArticlesItem)
            val textTitle: TextView = holder.itemView.findViewById(R.id.textArticlesItemTitle)
            val avatarUser: ImageView = holder.itemView.findViewById(R.id.avatarUserArticlesItem)
            val textUserName: TextView = holder.itemView.findViewById(R.id.textUserNameArticles)
            val textDate: TextView = holder.itemView.findViewById(R.id.textDateArticlesItem)
            val imageStatusSave: ImageView =
                holder.itemView.findViewById(R.id.iconStatusSaveArticles)
            val imageStatusHeart: ImageView =
                holder.itemView.findViewById(R.id.iconStatusHeartArticles)
            val item: CardView = holder.itemView.findViewById(R.id.itemArticles)

            val articles: Articles = list[position]
            if (articles == null) return@apply
            val email: String =
                UserDatabase.getInstance(context).userDAO().getUserCurrentAccount()!!.email

            // show data
            Glide.with(context).load(articles.image).centerCrop().into(imageItem)
            textTitle.text = articles.title
            Glide.with(context).load(articles.avatarUser).centerCrop().into(avatarUser)
            textUserName.text = articles.userName
            textDate.text = articles.date
            if (articles.listSave != null) {
                for (i in 0 until articles.listSave!!.size) {
                    if (articles.listSave!![i].matches(email.toRegex())) {
                        imageStatusSave.setImageResource(R.drawable.icon_saved)
                        break
                    } else {
                        imageStatusSave.setImageResource(R.drawable.icon_un_save)
                    }
                }
            }
            if (articles.listHeart != null) {
                for (i in 0 until articles.listHeart!!.size) {
                    if (articles.listHeart!![i].matches(email.toRegex())) {
                        imageStatusHeart.setImageResource(R.drawable.icon_liked)
                        imageStatusHeart.setColorFilter(resources.getColor(R.color.color_heart))
                        break
                    } else {
                        imageStatusHeart.setImageResource(R.drawable.icon_un_like)
                    }
                }
            }

            // set onclick
            item.setOnClickListener { clickItem.clickItem(articles) }
            imageStatusSave.setOnClickListener { clickItem.handleSave(articles) }
            imageStatusHeart.setOnClickListener { clickItem.handleLike(articles) }
        }
    }
}
