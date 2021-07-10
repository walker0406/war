package com.walker.war.data.api

import com.walker.war.data.model.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by admin on 2021/7/10.
 */
interface ApiService {
    @GET("user")
    suspend fun getUsers(): Response<List<User>>
}