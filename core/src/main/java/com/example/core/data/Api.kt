package com.example.core.data

import com.example.core.data.model.ResponseDTOModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("category") category: String = "business",
        @Query("country") country: String = "us"
    ): Response<ResponseDTOModel>

}