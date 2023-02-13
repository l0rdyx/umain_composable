package com.example.umaincomposable

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.umaincomposable.viewmodels.MainPageModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    // Take a look on Koin (Dependency Injection)
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
    // val coroutineScope = CoroutineScope(Dispatchers.Default)
    // YOU CAN CHANGE FOR THIS...
    val scope = rememberCoroutineScope()

    val uiState by mainPageModel.uiState.collectAsState()

    Button(onClick = {
        scope.launch {
            mainPageModel.getRestaurants()
        }
    }) {
        Text(text = "Load")
    }
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        /*
        rememberLazyListState
        Changes to the provided initial values will not result in the state being recreated or changed in any way if it has already been created.
         */
        state = rememberLazyListState()
    ) {
        items(
            items = uiState.data,
            key = {
                it.id
            }
        ) { restaurant ->
            Text(text = restaurant.name)
        }
    }

    /*
    for (restaurant in uiState.data) {
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
    */
}
