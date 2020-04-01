package com.example.forecastmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forecastmvvm.data.repository.ForecastRepository

@Suppress("UNCHECKED_CAST")
class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass:Class<T>):T{
        return CurrentWeatherViewModel(forecastRepository) as T
    }

}