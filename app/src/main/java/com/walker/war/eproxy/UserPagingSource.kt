/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.walker.war.eproxy

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.model.User
import kotlinx.coroutines.delay
import javax.inject.Inject

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UserPagingSource @Inject constructor(
    private val service: ApiHelper,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        Log.d("guowtest", "paginsource page=" + page)
        return try {
            val response = service.getUsers()
            delay(3000)
            val photos = response.body() as List<User>
            LoadResult.Page(
                data = photos,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (exception: Exception) {
            delay(3000)
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
