package com.example.cuteanimals.view.data.repo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteRepository {

    var retrofitService: RetrofitRepo? = null

    init {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        retrofitService = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitRepo::class.java)
    }

    suspend fun getCatList() =
        retrofitService?.getCatList()
}