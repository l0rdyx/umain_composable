package com.example.umaincomposable.datasource

import com.example.umaincomposable.models.Restaurant
import com.example.umaincomposable.models.RestaurantList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): RestaurantList
}