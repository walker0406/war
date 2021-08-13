package com.walker.net

/**
 * Created by admin on 2021/8/13.
 */
sealed class HttpResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : HttpResult<T>()
    data class Failure(val reason: String) : HttpResult<Nothing>()
    data class Error(val exception: Exception) : HttpResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[reason=$reason]"
            is Error -> "Error[exception=$exception]"
        }
    }
}