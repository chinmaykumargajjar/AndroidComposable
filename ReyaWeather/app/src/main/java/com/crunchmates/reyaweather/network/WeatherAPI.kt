package com.crunchmates.reyaweather.network

import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.model.WeatherObject
import com.crunchmates.reyaweather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY
    ): Weather
}