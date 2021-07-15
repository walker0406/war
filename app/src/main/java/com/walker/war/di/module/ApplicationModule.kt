package com.walker.war.di.module

import com.walker.war.BuildConfig
import com.walker.war.data.ApiHelperImpl
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.api.ApiService
import com.walker.war.di.qualifier.Test2
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by admin on 2021/7/10.
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Test2
    fun getString() = Any()

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyClassInterface {
        fun getFoo(): TestAny

    }
}

