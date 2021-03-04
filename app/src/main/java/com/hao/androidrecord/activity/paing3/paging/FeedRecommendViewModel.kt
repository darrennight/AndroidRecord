package com.hao.androidrecord.activity.paing3.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.hao.androidrecord.activity.paing3.model.ArticleEntity
import kotlinx.coroutines.flow.*

class FeedRecommendViewModel () : ViewModel() {

    private val feedDataRepository: FeedDataRepository by lazy { FeedDataRepository() }

    private var currentResult: Flow<PagingData<ArticleEntity>>? = null

    fun refreshFeed(): LiveData<PagingData<ArticleEntity>> {
        val newResult = feedDataRepository.loadFeedData().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult.asLiveData()
    }
}