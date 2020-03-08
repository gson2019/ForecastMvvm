package com.example.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

class ImperialCurrentWeatherEntry(
    @ColumnInfo(name="tempF")
    override val temperature: Double,
    @ColumnInfo(name="condition_text")
    override val conditionText: String,
    @ColumnInfo(name="condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name="windMph")
    override val windSpeed: Double
) : UnitSpecificCurrentWeatherEntry {
}