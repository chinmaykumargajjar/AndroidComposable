package com.crunchmates.reyaweather.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crunchmates.reyaweather.model.Unit
import com.crunchmates.reyaweather.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private  val repository: WeatherDbRepository)
    : ViewModel(){
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()
    
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    if(listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG","Empty Favs")
                    } else {
                        _unitList.value = listOfUnits
                        Log.d("TAG", ":${unitList.value}")
                    }
                }
        }
    }


    fun insertUnit(unit: Unit) = viewModelScope.launch {
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit)= viewModelScope.launch { repository.updateUnit(unit) }

    fun removeUnit(unit: Unit) = viewModelScope.launch { repository.deleteUnit(unit) }

    fun removeAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
}