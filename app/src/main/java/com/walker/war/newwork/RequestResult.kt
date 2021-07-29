package com.walker.war.newwork

/**
 * Created by admin on 2021/7/24.
 */


sealed class RequestResult<out T> {
    class Loading() : RequestResult<Nothing>()
    data class Success<out T>(val value: T) : RequestResult<T>()
    data class Failure(val throwable: Throwable?) : RequestResult<Nothing>()
}

