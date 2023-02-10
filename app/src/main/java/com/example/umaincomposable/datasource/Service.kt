package com.example.umaincomposable.datasource


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getRestaurants() = apiHelper.getRestaurants()
}