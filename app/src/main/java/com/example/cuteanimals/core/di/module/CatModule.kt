package com.example.cuteanimals.core.di.module

import com.example.cuteanimals.core.utils.Constants
import com.example.cuteanimals.data.repo.CatService
import com.example.cuteanimals.data.repo.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return client
    }

    @Singleton
    @Provides
    fun provideNetworkService(client: OkHttpClient): CatService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.CAT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CatService::class.java)
    }

    @Singleton
    @Provides
    fun provideDogNetworkService(client: OkHttpClient): DogService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.DOG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DogService::class.java)
    }
}