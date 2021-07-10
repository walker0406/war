package com.walker.war.data

import com.walker.war.data.api.ApiHelper
import javax.inject.Inject

/**
 * Created by admin on 2021/7/10.
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}