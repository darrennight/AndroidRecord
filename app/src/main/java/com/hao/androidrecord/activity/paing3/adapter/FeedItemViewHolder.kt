package com.hao.androidrecord.activity.paing3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.paing3.model.ArticleEntity
import kotlinx.android.synthetic.main.feed_list_item.view.*

class FeedItemViewHolder(view: View) : BaseViewHolder<ArticleEntity>(view){

    companion object {
        fun create(parent: ViewGroup): FeedItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_list_item, parent, false)
            return FeedItemViewHolder(view)
        }
    }

    override fun bind(data: ArticleEntity,position:Int) {
        itemView.tv_text.text = "$position${data.title}${data.chapterId}"
    }

}