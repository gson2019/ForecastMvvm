package com.example.forecastmvvm.data.db.entity

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double

}