package com.crunchmates.reyaweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crunchmates.reyaweather.screens.main.MainScreen
import com.crunchmates.reyaweather.screens.splash.WeatherSplashScreen

// The WeatherNavigation function is a Composable that manages the navigation for the app.
// It creates a NavHost with a navigation controller (navController) and specifies the
// startDestination as the SplashScreen from the WeatherScreens enum. The NavHost defines
// the available navigation routes for the app, and in this case, it contains a single route
// for the SplashScreen. When this route is triggered, it will display the WeatherSplashScreen
// composable, which currently just shows the text "splash screen".
@Composable
fun WeatherNavigation() {
    // Remember the navigation controller to manage navigation between composable screens.
    val navController = rememberNavController()

    // NavHost is responsible for defining the navigation graph.
    // It takes the navigation controller and defines the starting screen (SplashScreen).
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        // Define a composable route for the SplashScreen.
        composable(WeatherScreens.SplashScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreens.MainScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            MainScreen(navController)
        }
    }
}