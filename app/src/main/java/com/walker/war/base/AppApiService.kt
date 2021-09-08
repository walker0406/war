package com.walker.war.base

import com.walker.war.BuildConfig
import com.walker.war.data.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Created by admin on 2021/8/2.
 */
interface AppApiService {
    @GET("user")
    suspend fun getUsers(): Response<List<User>>

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL

        val apiService by lazy {
            create()
        }

        fun create(): AppApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(AppApiService::class.java)
        }
    }

}