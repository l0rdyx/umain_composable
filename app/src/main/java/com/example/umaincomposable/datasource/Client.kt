package com.example.umaincomposable.datasource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    private const val BASE_URL = "https://food-delivery.umain.io/api/v1/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: ApiService = getClient().create(ApiService::class.java)
}

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRestaurants() = apiService.getRestaurants()
}
