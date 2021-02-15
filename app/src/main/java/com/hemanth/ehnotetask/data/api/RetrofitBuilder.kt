package com.hemanth.gormalonetask.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private fun getService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ehnotedocapi.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLogClient())
            .build()
    }

    private fun getLogClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        client.retryOnConnectionFailure(false)
        client.connectTimeout(150, TimeUnit.SECONDS).readTimeout(150, TimeUnit.SECONDS)
        return client.build()
    }

    val apiService = getService().create(EndPoints::class.java)
}