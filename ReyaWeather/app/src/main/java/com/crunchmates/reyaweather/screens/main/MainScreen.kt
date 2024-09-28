package com.crunchmates.reyaweather.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.widget.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>> (
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "Toronto")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Helena, MT",
                navController = navController,
                elevation = 5.dp
            )
        }
    ) { paddingValues ->  // `paddingValues` represents the padding that Scaffold applies.
        MainContent(data = weather, paddingValues = paddingValues)
    }
}

@Composable
fun MainContent(data: Weather, paddingValues: PaddingValues) {
    // Apply the padding from Scaffold to ensure the content doesn't get obscured.
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(text = data.city.name)
        // Add more content if needed
    }
}

