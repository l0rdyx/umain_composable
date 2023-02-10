package com.example.umaincomposable.viewmodels

import androidx.lifecycle.ViewModel
import com.example.umaincomposable.datasource.ApiHelper
import com.example.umaincomposable.datasource.MainRepository
import com.example.umaincomposable.datasource.RetrofitBuilder
import com.example.umaincomposable.models.Restaurant
import com.example.umaincomposable.models.RestaurantList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPageModel : ViewModel() {
    var result: RestaurantList? = null
    suspend fun getRestaurants(){
            val apiHelper: ApiHelper = ApiHelper(RetrofitBuilder.apiService)
            val mainRepository = MainRepository(apiHelper)
            println("IM HERE")
            result = mainRepository.getRestaurants()
    }
}