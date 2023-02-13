package com.example.umaincomposable.datasource

// Always keep the name of the file as the class name
class MainRepository(private val apiHelper: ApiHelper) {

    // This function needs to be suspend as well
    suspend fun getRestaurants() = apiHelper.getRestaurants()
}
