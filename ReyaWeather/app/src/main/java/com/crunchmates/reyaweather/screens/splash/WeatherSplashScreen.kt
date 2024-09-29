package com.crunchmates.reyaweather.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crunchmates.reyaweather.R
import com.crunchmates.reyaweather.navigation.WeatherScreens
import kotlinx.coroutines.delay

// The WeatherSplashScreen function is a simple Composable that displays the splash screen.
// It takes the navigation controller as a parameter (although it's not currently used in this function).
@Composable
fun WeatherSplashScreen(navController: NavController){
    // Display the text "splash screen".
    val scale = remember {
        Animatable(initialValue = 0f)
    } //Control Scale of Animation.

    //Structure for Animation
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                }
            ))
        delay(2000L)
        val defaultCity = "Toronto"
        navController.navigate(route = WeatherScreens.MainScreen.name+"/$defaultCity")
    })

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .scale(scale.value)
            .size(330.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sunny Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(95.dp)
            )
            Text(
                text = "Find the Sun?",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )
        }
    }
}
