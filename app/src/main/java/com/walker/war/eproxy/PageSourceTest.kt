package com.walker.war.eproxy

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.model.User

/**
 * Created by admin on 2021/7/16.
 */

class PageSourceTest(
    val backend: ApiHelper
) : PagingSource<Int, User>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        try {
            // 未定义时加载第 1 页
            val nextPageNumber = params.key ?: 1
            val response = backend.getUsers()
            return LoadResult.Page(
                data = response.body()!!,
                prevKey = null, // 仅向后翻页
                nextKey = null
            )
        } catch (e: Exception) {
            // 在此块中处理错误
            return LoadResult.Error(java.lang.Exception())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
         return 1.toInt()

    }
}