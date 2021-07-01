package com.myproject.antinolabs.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.antinolabs.Event
import com.myproject.antinolabs.data.Model
import com.myproject.antinolabs.data.remote.NetworkState
import com.myproject.antinolabs.data.remote.RetrofitService
import com.myproject.antinolabs.errorHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val apiService: RetrofitService) : ViewModel() {

    private val _newsState = MutableLiveData<Event<NetworkState<List<Model>>>>()
    val newsState: LiveData<Event<NetworkState<List<Model>>>> get() = _newsState

    fun getNews() {
        _newsState.value = Event(NetworkState.Loading())
        viewModelScope.launch {
            try {
                val response = apiService.getAllNews(
                    "tesla", "2021-06-23", "publishedAT",
                    "1ce6db6a71ea4e6ab0db59ea207fe682"
                )
                if ("ok" == response.status) {
                    _newsState.value = Event(NetworkState.Success(response.articles))

                } else {
                    _newsState.value = Event(NetworkState.Error(response.status))
                }
            } catch (ex: Exception) {
                _newsState.value = Event(errorHandler(ex))
            }
        }
    }
}