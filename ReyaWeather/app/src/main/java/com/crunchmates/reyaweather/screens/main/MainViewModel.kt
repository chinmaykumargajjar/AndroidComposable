package com.crunchmates.reyaweather.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.City
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.model.WeatherObject
import com.crunchmates.reyaweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel() {
    suspend fun getWeatherData(city: String):
            DataOrException<Weather,Boolean, Exception> {
        return repository.getWeather(cityQuery = city);
    }
}