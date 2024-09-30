package com.crunchmates.reyaweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crunchmates.reyaweather.model.Favorite
import com.crunchmates.reyaweather.model.Unit


@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

