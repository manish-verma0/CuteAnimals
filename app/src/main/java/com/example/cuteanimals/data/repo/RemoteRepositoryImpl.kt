package com.example.cuteanimals.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cuteanimals.data.model.Cat
import com.example.cuteanimals.data.model.CatsPagingSource
import com.example.cuteanimals.data.model.DogsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val catService: CatService, private val dogService: DogService) : CatRepository{

    suspend fun getCatList(): Flow<List<Cat>> {
        return flow {
            emit(
                catService.getCatList(0).body() ?: emptyList()
            )
        }
    }

    override fun getCats(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CatsPagingSource(service = catService)
            }
        ).flow
    }

    override fun getDogs(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DogsPagingSource(service = dogService)
            }
        ).flow
    }
}