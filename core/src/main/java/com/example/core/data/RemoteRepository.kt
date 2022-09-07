package com.example.core.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RemoteRepository {

    fun <T> createService(clazz: Class<T>): T

    class Base() : RemoteRepository {

        override fun <T> createService(clazz: Class<T>): T {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build()
                .create(clazz)
        }

        private fun client(): OkHttpClient {
            return OkHttpClient()
                .newBuilder()
                .addInterceptor {
                    val newRequest = it.request().newBuilder()
                    newRequest.addHeader("Authorization", "5769b78a970e4a4e83274770741b02c0")
                    it.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

    }

}

inline fun <reified T> RemoteRepository.createTService(): T {
    return createService(T::class.java)
}