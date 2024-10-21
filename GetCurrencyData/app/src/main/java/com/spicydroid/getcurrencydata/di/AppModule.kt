package com.spicydroid.getcurrencydata.di

import com.spicydroid.getcurrencydata.api.InvestmentAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //Class that provides dependencies
@InstallIn(SingletonComponent::class)
object AppModule {
    val loggingInterceptor = HttpLoggingInterceptor()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides // Tells how to provide instance of a type
    @Singleton // It should be singleton within hilt container
    fun provideOpenWeatherApi(): InvestmentAPI {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gist.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InvestmentAPI::class.java)
    }
}