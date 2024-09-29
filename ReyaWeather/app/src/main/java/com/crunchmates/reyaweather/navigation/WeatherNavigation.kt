package com.crunchmates.reyaweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crunchmates.reyaweather.screens.FavoriteScreen.FavoriteScreen
import com.crunchmates.reyaweather.screens.about.AboutScreen
import com.crunchmates.reyaweather.screens.main.MainScreen
import com.crunchmates.reyaweather.screens.main.MainViewModel
import com.crunchmates.reyaweather.screens.search.SearchScreen
import com.crunchmates.reyaweather.screens.settings.SettingsScreen
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

        //
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument( name = "city") {
                    type = NavType.StringType
                })) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city=city)
            }

            // Navigate to the WeatherSplashScreen composable when this route is triggered.

        }

        composable(WeatherScreens.SearchScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            SettingsScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            // Navigate to the WeatherSplashScreen composable when this route is triggered.
            AboutScreen(navController = navController)
        }
    }
}