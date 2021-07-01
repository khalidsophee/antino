package com.myproject.antinolabs.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: String?,

    @SerializedName("articles")
    @Expose
    val articles: T?

)