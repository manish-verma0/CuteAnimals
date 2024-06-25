package com.example.cuteanimals.data.repo

import com.example.cuteanimals.core.utils.Constants
import com.example.cuteanimals.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRepo {

    @GET(Constants.CAT_URL)
    suspend fun getCatList() : Response<List<Cat>>
}