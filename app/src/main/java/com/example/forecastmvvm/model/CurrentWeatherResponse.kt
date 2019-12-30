package com.example.forecastmvvm.model


data class CurrentWeatherResponse(
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)