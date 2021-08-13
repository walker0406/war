package com.walker.net.repository

import com.walker.net.HttpResult

/**
 * Created by admin on 2021/8/13.
 */
abstract class BaseRepository {

    suspend fun <T : Any> call(request: suspend () -> Any): HttpResult<T> {
        return try {
            executeResponse(request())
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
    }

    abstract fun <T : Any> executeResponse(response: Any): HttpResult<T>
}