package com.crunchmates.reyaweather.screens.main

import androidx.lifecycle.ViewModel
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel() {
    suspend fun getWeatherData(city: String, units: String):
            DataOrException<Weather,Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units);
    }
}