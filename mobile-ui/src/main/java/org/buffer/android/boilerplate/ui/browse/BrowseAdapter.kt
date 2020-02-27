package org.buffer.android.boilerplate.ui.browse

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.buffer.android.boilerplate.ui.R
import org.buffer.android.boilerplate.ui.model.ArticleViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var articles: List<ArticleViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.nameText.text = article.author
        holder.titleText.text = article.title
        Glide.with(holder.itemView.context)
                .load(article.urlToImage)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_bufferoo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView
        var nameText: TextView
        var titleText: TextView

        init {
            avatarImage = view.findViewById(R.id.image_avatar)
            nameText = view.findViewById(R.id.text_name)
            titleText = view.findViewById(R.id.text_title)
        }
    }

}