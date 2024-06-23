package com.example.cuteanimals.view.data.repo

import com.example.cuteanimals.view.core.utils.Constants
import com.example.cuteanimals.view.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitRepo {

    @GET(Constants.CAT_URL)
    suspend fun getCatList() : Response<List<Cat>>
}