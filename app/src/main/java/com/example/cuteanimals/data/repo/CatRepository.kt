package com.example.cuteanimals.data.repo

import androidx.paging.PagingData
import com.example.cuteanimals.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCats(): Flow<PagingData<Cat>>

}