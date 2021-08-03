package com.walker.war.di.module

import android.util.Log
import com.walker.war.BuildConfig
import com.walker.war.base.AppApiService
import com.walker.war.data.api.ApiHelper
import com.walker.war.data.api.ApiService
import com.walker.war.di.qualifier.Test2
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.InetAddress
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
            .eventListener(object : EventListener() {
                override fun callStart(call: Call) {
                    super.callStart(call)
                }

                override fun dnsStart(call: Call, domainName: String) {
                    super.dnsStart(call, domainName)
                }

                override fun dnsEnd(
                    call: Call,
                    domainName: String,
                    inetAddressList: List<InetAddress>
                ) {
                    super.dnsEnd(call, domainName, inetAddressList)
                }

            })
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

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @EntryPoint
    @Singleton
    @InstallIn(SingletonComponent::class)
    interface MyClassInterface {
        fun getFoo(): TestAny
    }


    @Singleton
    @Provides
    fun provideAppApiService(): AppApiService {
        return AppApiService.create()
    }


}

