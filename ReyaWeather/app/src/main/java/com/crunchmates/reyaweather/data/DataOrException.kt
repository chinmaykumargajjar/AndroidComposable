package com.crunchmates.reyaweather.data

import coil.compose.ImagePainter

class DataOrException<T, Boolean, E: Exception> (
    var data: T ?= null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null) {

}
