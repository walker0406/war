package com.walker.war.di.module

import com.walker.war.data.api.ApiService
import com.walker.war.data.model.User
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */

class TestAny @Inject constructor(val apiService: ApiService) {
    suspend fun getUser(): Response<List<User>> {
        return apiService.getUsers()
    }
}