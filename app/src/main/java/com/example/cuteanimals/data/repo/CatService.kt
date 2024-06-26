package com.example.cuteanimals.data.repo

import com.example.cuteanimals.core.utils.Constants
import com.example.cuteanimals.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET(Constants.CAT_URL)
    suspend fun getCatList(@Query("page") page: Int) : Response<List<Cat>>
}