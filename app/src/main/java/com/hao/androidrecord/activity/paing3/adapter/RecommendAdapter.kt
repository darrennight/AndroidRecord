package com.hao.androidrecord.activity.paing3.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.hao.androidrecord.activity.paing3.model.ArticleEntity

class RecommendAdapter :PagingDataAdapter<ArticleEntity, BaseViewHolder<ArticleEntity>>(
    DEFAULT_ITEM_DIFF_CALLBACK
) {

    companion object {
        val DEFAULT_ITEM_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldPagingItem: ArticleEntity, newPagingItem: ArticleEntity): Boolean =
                oldPagingItem == newPagingItem

            override fun areContentsTheSame(oldPagingItem: ArticleEntity, newPagingItem: ArticleEntity): Boolean =
                oldPagingItem == newPagingItem
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ArticleEntity>, position: Int) {
        getItem(position)?.let {
            holder.bind(it,position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ArticleEntity> {
        return FeedItemViewHolder.create(parent)
    }
}