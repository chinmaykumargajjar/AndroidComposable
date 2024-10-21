package com.crunchmates.reyaweather.screens.main

import android.os.Trace
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.model.WeatherItem
import com.crunchmates.reyaweather.navigation.WeatherScreens
import com.crunchmates.reyaweather.screens.settings.SettingsViewModel
import com.crunchmates.reyaweather.utils.formatDate
import com.crunchmates.reyaweather.utils.formatDecimals
import com.crunchmates.reyaweather.widget.HumidityWindPressureRow
import com.crunchmates.reyaweather.widget.SunsetAndSunriseRow
import com.crunchmates.reyaweather.widget.WeatherAppBar
import com.crunchmates.reyaweather.widget.WeatherDetailRow
import com.crunchmates.reyaweather.widget.WeatherStateImage
import java.util.Locale

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    //Trace.beginSection("ConfigureMainScreen")
    var curCity: String = if(city!!.isBlank()) "Toronto" else city
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("Imperial")
    }

    var isImperial by remember {
        mutableStateOf(false)
    }

    if(!unitFromDb.isNullOrEmpty()) {
        Log.d("TAG","MainScreen: ${city.toString()}")
        unit = unitFromDb[0].unit.split(" ")[0].lowercase(Locale.ROOT)

        isImperial = unit == "imperial"
        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>> (
            initialValue = DataOrException(loading = true)
        ) {
            value = mainViewModel.getWeatherData(city = city.toString(),
                units = unit
            )
        }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data!!, navController, isImperial)
        }
    }
    Trace.endSection()
}
@Composable
fun MainScaffold(weather: Weather, navController: NavController, isImperial: Boolean) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name+",${weather.city.country}",
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name);
                },
                elevation = 5.dp
            ) { // Trailing Lambda of On Button Clicked since it was last lambda passed
                Log.d("TAG", "MainScaffold: Button Clicked")
            }
        }
    ) { paddingValues ->  // `paddingValues` represents the padding that Scaffold applies.
        MainContent(data = weather, paddingValues = paddingValues, isImperial)
    }
}

@Composable
fun MainContent(data: Weather, paddingValues: PaddingValues, isImperial: Boolean) {
    // Apply the padding from Scaffold to ensure the content doesn't get obscured.
    val imageUrl = "https://openweathermap.org/img/wn/${data!!.list[0].weather[0].icon}.png"
    val weatherItem = data.list[0]

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(6.dp),
            fontWeight = FontWeight.SemiBold
        )

        Surface (
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                //Image Loading from Url
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.temp.day)+"º", style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        // Add more content if needed
        HumidityWindPressureRow(weather = weatherItem, isImperial)
        Divider()
        SunsetAndSunriseRow(weather = weatherItem)

        Text(text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold)

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ){
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }
    }
}
