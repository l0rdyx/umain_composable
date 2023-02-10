package com.example.umaincomposable

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.umaincomposable.datasource.*
import com.example.umaincomposable.models.Restaurant
import com.example.umaincomposable.viewmodels.MainPageModel
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    var mainPageModel = MainPageModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Column() {
                    Row() {
                        Logo()
                    }
                    OptionsLane()
                    Column() {
                        RestaurantsList(mainPageModel)
                    }
                }
        }

    }
}



@Composable
fun Logo() {
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
}

@Composable 
fun OptionsLane() {
    Row() {
        Button(onClick = { /*TODO*/ }) {
            Image(painter = painterResource(id = R.drawable.top_rated), contentDescription = "top_rated")
            Text(text = "Top rated")
        }
        Button(onClick = { /*TODO*/ }) {
            Image(painter = painterResource(id = R.drawable.take_out), contentDescription = "take_out")
            Text(text = "Take out")
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RestaurantsList(mainPageModel: MainPageModel) {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    coroutineScope.launch {
        mainPageModel.getRestaurants()
        println(mainPageModel.result)
    }
    val res = mainPageModel.result?.restaurants?.toMutableStateList()
    println("SUPPOSED TO BE HERE")
    println(res)
    if (res != null) {
        for (restaurant in res) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column() {
                    Image(painter = painterResource(id = R.drawable.take_out), contentDescription = "")
                    Row() {
                        Text(text = restaurant.name)
                        Image(painter = painterResource(id = R.drawable.star), contentDescription = "")
                        Text(text = restaurant.rating.toString())
                    }
                    Row() {
                        for (option in restaurant.filterIds) {
                            Text(text = option)
                        }
                    }
                    Row() {
                        Image(painter = painterResource(id = R.drawable.clock), contentDescription = "")
                        Text(text = restaurant.deliveryTime)
                    }
                }

            }
        }
    }
}
