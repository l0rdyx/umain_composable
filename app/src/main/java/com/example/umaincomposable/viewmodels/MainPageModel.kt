package com.example.umaincomposable.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umaincomposable.datasource.ApiHelper
import com.example.umaincomposable.datasource.MainRepository
import com.example.umaincomposable.datasource.RetrofitBuilder
import com.example.umaincomposable.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val data: List<Restaurant> = emptyList(),
    val loading: Boolean = true
) {
    val isEmpty = !loading && data.isEmpty()
}
class MainPageModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getRestaurants()
        }
    }

    suspend fun getRestaurants() {
        try {
            val apiHelper = ApiHelper(RetrofitBuilder.apiService)
            val mainRepository = MainRepository(apiHelper)
            _uiState.update {
                it.copy(
                    data = mainRepository.getRestaurants().restaurants
                )
            }
        } finally {
            Log.d("MainPageModel", "Loading done")
        }
    }
}
