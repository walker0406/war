package com.walker.war.repository

import com.walker.base.BuildConfig
import com.walker.net.HttpClient
import com.walker.net.HttpResult
import com.walker.net.api.ApiService
import com.walker.net.repository.BaseRepository
import com.walker.net.respond.XXXResponse

/**
 * Created by admin on 2021/8/13.
 */
object XXXRepository : BaseRepository() {
    private val service = HttpClient.getService<ApiService>(BuildConfig.BASE_URL)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> executeResponse(response: Any): HttpResult<T> {
        return if ((response as XXXResponse<T>).code != 200) {
            HttpResult.Failure(response.toString())
        } else {
            HttpResult.Success(response.data)
        }
    }


//    suspend fun getHomeFeed(cityId: Int): HttpResult<HomeFeedData> {
//        return call { service.getHomeFeed(cityId) }
//    }
}