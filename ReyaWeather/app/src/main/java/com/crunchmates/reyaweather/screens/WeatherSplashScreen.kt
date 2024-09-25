package com.crunchmates.reyaweather.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

// The WeatherSplashScreen function is a simple Composable that displays the splash screen.
// It takes the navigation controller as a parameter (although it's not currently used in this function).
@Composable
fun WeatherSplashScreen(navController: NavController){
    // Display the text "splash screen".
    Text(text = "splash screen")
}
