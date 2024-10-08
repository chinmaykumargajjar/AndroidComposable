package com.crunchmates.reyaweather.di

import android.content.Context
import androidx.room.Room
import com.crunchmates.reyaweather.data.WeatherDao
import com.crunchmates.reyaweather.data.WeatherDatabase
import com.crunchmates.reyaweather.model.Weather
import com.crunchmates.reyaweather.network.WeatherAPI
import com.crunchmates.reyaweather.repository.WeatherDbRepository
import com.crunchmates.reyaweather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //Class that provides dependencies
@InstallIn(SingletonComponent::class) // Making this available applciation wide
class AppModule {
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao
    = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabsase(@ApplicationContext context: Context): WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database")
        .fallbackToDestructiveMigration()
        .build()

    // **Add this method to provide WeatherDbRepository**
    @Singleton
    @Provides
    fun provideWeatherDbRepository(weatherDao: WeatherDao): WeatherDbRepository {
        return WeatherDbRepository(weatherDao)
    }

    val loggingInterceptor = HttpLoggingInterceptor()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides // Tells how to provide instance of a type
    @Singleton // It should be singleton within hilt container
    fun provideOpenWeatherApi(): WeatherAPI {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

}

/*
What the Code Does
The code defines a module (AppModule) that provides a WeatherAPI instance, which is used to fetch weather data from an API. The module is set up using Hilt annotations to ensure that dependencies like the WeatherAPI are created and managed efficiently.

Key Components
Hilt Dependency Injection Hilt simplifies the process of injecting dependencies. Instead of manually instantiating objects, Hilt handles it automatically. In this case, we use Hilt to provide an instance of WeatherAPI that can be injected wherever it's needed in the app.
Retrofit Retrofit is a popular HTTP client library for Android that simplifies network requests and the conversion of data into usable objects.
AppModule Class This class is annotated with @Module and @InstallIn(SingletonComponent::class). This tells Hilt that this module will be part of the Singleton Component, meaning the dependencies it provides will have a singleton scope (i.e., a single instance will exist throughout the app's lifecycle).
@Provides and @Singleton Annotations The @Provides annotation tells Hilt how to provide an instance of a class—in this case, WeatherAPI. The @Singleton annotation ensures that only one instance of WeatherAPI is created and shared across the app.
How It Works
@Module Annotation This marks the class as a Hilt module, indicating that it contains methods that provide dependencies.
@InstallIn(SingletonComponent::class) Annotation This tells Hilt to install this module into the SingletonComponent. This component lives as long as the entire app, meaning that all provided dependencies (like WeatherAPI) will also live throughout the app's lifecycle.
provideOpenWeatherApi() Method This method provides an instance of WeatherAPI when it is needed elsewhere in the app. The method:
Uses Retrofit.Builder() to create a Retrofit instance.
Sets the base URL for API requests using Constants.BASE_URL.
Adds a GsonConverterFactory to handle the conversion of JSON data into Kotlin/Java objects.
Finally, it builds the Retrofit instance and creates the WeatherAPI interface.
Singleton Pattern By using @Singleton, we ensure that the WeatherAPI instance is a singleton, meaning the app will use a single, shared instance of WeatherAPI across the whole application.
 */