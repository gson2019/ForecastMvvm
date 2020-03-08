package com.example.forecastmvvm.api

import com.example.forecastmvvm.BuildConfig.API_KEY
import com.example.forecastmvvm.BuildConfig.BASE_URL
import com.example.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
    ): Deferred<CurrentWeatherResponse>

    // static method
    companion object {
        operator fun invoke(): WeatherService {
            val requestInterceptor = Interceptor{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}