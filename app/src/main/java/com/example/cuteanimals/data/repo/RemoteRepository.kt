package com.example.cuteanimals.data.repo

import com.example.cuteanimals.data.model.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val retrofitRepo: RetrofitRepo){

    suspend fun getCatList(): Flow<List<Cat>> {
        return flow {
            emit(
                retrofitRepo.getCatList().body() ?: emptyList()
            )
        }
    }
}