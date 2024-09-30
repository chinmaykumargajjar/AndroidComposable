package com.crunchmates.reyaweather.repository

import android.util.Log
import com.crunchmates.reyaweather.data.DataOrException
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private  val api: WeatherAPI) {
    suspend fun getWeather(cityQuery: String, units: String)
    : DataOrException<Weather, Boolean, Exception>{
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        }catch (e: Exception) {
            Log.d("INSIDE", "getWeather : $e")
            return DataOrException(e = e)
        }

        return DataOrException(data = response, loading = false)
    }
}