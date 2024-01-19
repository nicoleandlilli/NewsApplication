package com.test.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.news.R
import com.test.news.databinding.NewsItemBinding
import com.test.news.realmmodel.ArticleModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class NewsTopHeadLineAdapter(data:OrderedRealmCollection<ArticleModel?>?):
    RealmRecyclerViewAdapter<ArticleModel?,NewsTopHeadLineAdapter.ArticleViewHolder>(data,true){

    inner class ArticleViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var articleModel: ArticleModel? =getItem(position)

        articleModel?.let {
            holder.binding.tvContent.text = articleModel.title
            Glide.with(holder.itemView)
                .load(articleModel.urlToImage)
                .placeholder(R.mipmap.ic_picture)
                .error(R.mipmap.ic_picture)
                .into(holder.binding.ivIcon)
        }

    }

}