package com.example.forecastmvvm.data.db.unitlocalized

import com.google.gson.annotations.SerializedName

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val windSpeed: Double
    val windDir: String
    val feelsLikeTemperature: Double
    val precipitationVolumne: Double
    val weatherDescriptions: List<String>
    val weatherIcons: List<String>
}