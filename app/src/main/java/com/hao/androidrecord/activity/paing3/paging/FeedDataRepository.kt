package com.hao.androidrecord.activity.paing3.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hao.androidrecord.activity.paing3.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

class FeedDataRepository{

    fun loadFeedData(): Flow<PagingData<ArticleEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 15),
            initialKey = 0,
            pagingSourceFactory = { MainSource() }
        ).flow
    }
}