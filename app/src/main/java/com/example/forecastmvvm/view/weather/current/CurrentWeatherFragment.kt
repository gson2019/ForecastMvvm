package com.example.forecastmvvm.view.weather.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.forecastmvvm.R
import com.example.forecastmvvm.internal.glide.GlideApp
import com.example.forecastmvvm.view.base.ScopedFragment
import com.example.forecastmvvm.viewmodel.CurrentWeatherViewModel
import com.example.forecastmvvm.viewmodel.CurrentWeatherViewModelFactory
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory:CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(CurrentWeatherViewModel::class.java)
//        // TODO: Use the ViewModel
//        val apiService = WeatherService(ConnectivityInterceptorImpl(this.context!!))
//        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
//        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer{
//            Log.d("Weather", it.toString())
//        })
//        GlobalScope.launch(Dispatchers.IO){
//            weatherNetworkDataSource.fetchCurrentWeather("Los Angeles")
////            val currentWeatherRes = apiService.getCurrentWeather("London").await()
//            withContext(Dispatchers.Main) {
////                currentWeatherTv.text = currentWeatherRes.toString()
//            }
//        }
        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(viewLifecycleOwner, Observer{
            if(it == null) return@Observer
            group_loading.visibility = View.GONE
            updateLocation("San Francisco")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updatePrecipitation(it.precipitationVolumne)
            updatedWind(it.windDir, it.windSpeed)
            GlideApp.with(this@CurrentWeatherFragment)
                .load("${it.weatherIcons.get(0)}")
                .into(imageView_condition_icon)
            Log.d("WeatherObserver", it.toString())
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String{
        return if (viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location

    }

    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"

    }

    private fun updatedWind(windDirection: String, windSpeed: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind: $windDirection, $windSpeed"
    }
    private fun updateTemperatures(temperature: Double, feelslike: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("C", "F")
        textView_temperature.text = "$temperature\u00B0$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like "  + "$feelslike\u00B0$unitAbbreviation"
    }

    private fun updatePrecipitation(precipitationVolume: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        textView_precipitation.text = "Preciptiation: $precipitationVolume $unitAbbreviation"
    }

    private fun updatedVisibility(visibility: Double){
//        val unitAbbreviation = chooseLocalizedUnitAbbreviation()
    }

}
