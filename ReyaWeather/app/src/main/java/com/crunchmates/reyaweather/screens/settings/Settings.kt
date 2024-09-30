package com.crunchmates.reyaweather.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.crunchmates.reyaweather.widget.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavController,
                   settingsViewModel: SettingsViewModel = hiltViewModel()){
    var unitToggleState by remember {
        mutableStateOf(false)
    }
    val measurementUnits = listOf("Imperial (F)","Metric (C)")
    var choiceState by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            false,
            navController = navController
        )
    }) { paddingValues ->
        Surface (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(paddingValues)
            ) {
                Text(text = "Change Units Of Measurement",
                    modifier = Modifier.padding(bottom = 15.dp))

                IconToggleButton(checked = !unitToggleState, onCheckedChange = {
                    unitToggleState = !it
                    if(unitToggleState) {choiceState = "Imperial (F)"} else {choiceState ="Metric (C)"}

                },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f))) {
                    Text(text = if (unitToggleState) "Fahrenheit ºF" else "Celsius ºC")
                }




            }
        }
    }
}