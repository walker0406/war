package com.walker.war.data

import com.walker.war.data.api.ApiHelper
import com.walker.war.data.api.ApiService
import com.walker.war.data.model.User
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}