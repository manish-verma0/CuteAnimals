package com.example.cuteanimals.core.di.module

import com.example.cuteanimals.core.utils.Constants
import com.example.cuteanimals.data.repo.RemoteRepository
import com.example.cuteanimals.data.repo.RetrofitRepo
import com.example.cuteanimals.view.viewmodel.CatViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatModule {

    @Singleton
    @Provides
    fun provideNetworkService(): RetrofitRepo {
        return Retrofit
            .Builder()
            .baseUrl(Constants.CAT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitRepo::class.java)
    }
}