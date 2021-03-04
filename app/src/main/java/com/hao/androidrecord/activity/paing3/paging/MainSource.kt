package com.hao.androidrecord.activity.paing3.paging

import androidx.paging.PagingSource
import com.hao.androidrecord.activity.paing3.model.Api
import com.hao.androidrecord.activity.paing3.model.ArticleEntity
import kotlinx.coroutines.delay

/**
 * Author   : kyle
 * Date     : 2020/6/15
 * Function : 分页
 */


class MainSource :PagingSource<Int, ArticleEntity>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {

        // 如果key是null，那就加载第0页的数据
        val page = params.key ?: 0
        // 每一页的数据长度
        val pageSize = params.loadSize
        return try {
            // 如果成功加载，那么返回一个LoadResult.Page,如果失败就返回一个Error
            // Page里传进列表数据，以及上一页和下一页的页数,具体的是否最后一页或者其他逻辑就自行判断
            // 需要注意的是，如果是第一页，prevKey就传null，如果是最后一页那么nextKey也传null
            // 其他情况prevKey就是page-1，nextKey就是page+1

            delay(500)
            val response = Api.getHomeArticles(page)
            LoadResult.Page(
                data = response.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.over) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}



