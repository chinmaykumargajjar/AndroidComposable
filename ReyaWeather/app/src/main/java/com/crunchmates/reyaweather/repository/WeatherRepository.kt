package com.crunchmates.reyaweather.repository

import androidx.core.text.util.LocalePreferences.TemperatureUnit.TemperatureUnits
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.City
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.model.WeatherItem
import com.crunchmates.reyaweather.model.WeatherObject
import com.crunchmates.reyaweather.network.WeatherAPI
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepository @Inject constructor(private  val api: WeatherAPI) {
    suspend fun getWeather(cityQuery: String)
    : DataOrException<Weather, Boolean, Exception>{
        val response = try {
            api.getWeather(query = cityQuery)
        }catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}