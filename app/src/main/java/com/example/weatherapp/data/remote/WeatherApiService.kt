package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>
}