package com.example.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

//英制的
data class ImperialCurrentWeatherEntry(
    @ColumnInfo(name="temperature")
    override val temperature: Double,
    @ColumnInfo(name="windSpeed")
    override val windSpeed: Double,
    @ColumnInfo(name="windDir")
    override val windDir: String,
    @ColumnInfo(name="feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name="precip")
    override val precipitationVolumne: Double,
    @ColumnInfo(name="weatherDescriptions")
    override val weatherDescriptions: List<String>,
    @ColumnInfo(name="weatherIcons")
    override val weatherIcons: List<String>
) : UnitSpecificCurrentWeatherEntry