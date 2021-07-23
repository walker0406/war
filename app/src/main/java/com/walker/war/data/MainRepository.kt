package com.walker.war.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.model.User
import com.walker.war.eproxy.UserPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()


    fun getData(query: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 5),
            pagingSourceFactory = { UserPagingSource(apiHelper) }
        ).flow
    }

}