package com.example.umaincomposable.datasource

import com.example.umaincomposable.models.RestaurantList
import retrofit2.http.GET
// Always keep the name of the file as the class name
interface ApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): RestaurantList
}
