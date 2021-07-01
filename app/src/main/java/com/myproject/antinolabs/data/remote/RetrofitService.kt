package com.myproject.antinolabs.data.remote

import com.myproject.antinolabs.data.Model
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse<List<Model>>


}


