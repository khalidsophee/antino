package com.myproject.antinolabs.data.remote
sealed class NetworkState<out T> {
    class Loading<out T> : NetworkState<T>()
    data class Success<out T>(
        val articles: T?,
        val totalResults: String?=null,
        val status: String? = null
    ) : NetworkState<T>()

    data class Failure<out T>(val throwable: String?) : NetworkState<T>()
    data class Error<out T>(
        val message: String?,
        val errorCode: String = "",
        val status: String? = null,
        val isSessionExpired: Boolean = false
    ) : NetworkState<T>()
}