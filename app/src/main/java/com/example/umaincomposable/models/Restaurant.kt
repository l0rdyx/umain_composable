package com.example.umaincomposable.models

data class Restaurant(
    val id: String,
    val name: String,
    val rating: Float,
    val filterIds: List<String>,
    val imageUrl: String,
    val deliveryTime: String
)

data class RestaurantList(
    val restaurants: List<Restaurant>
)
//val restaurants = arrayOf(Restaurant("Beans", arrayOf("Takeout", "Random"), "3.5", "30m"),
//            Restaurant("Milk", arrayOf("Takeout"), "55", "30m"),
//            Restaurant("Peach", arrayOf("Takeout", "Random", "Wow!"), "1", "25m"))
