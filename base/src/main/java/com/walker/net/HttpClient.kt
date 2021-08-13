package com.walker.net

import com.walker.base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by admin on 2021/8/13.
 */
object HttpClient {
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()
    }


    private val retrofits = HashMap<String, Retrofit>(8)

    fun <S> getService(service: Class<S>, baseUrl: String): S {
        return retrofits.getOrPut(baseUrl) {
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }.create(service)
    }

    inline fun <reified S> getService(baseUrl: String): S = getService(S::class.java, baseUrl)
}