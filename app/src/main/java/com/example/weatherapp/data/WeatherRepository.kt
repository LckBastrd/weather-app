package com.example.weatherapp.data

import com.example.weatherapp.data.remote.RetrofitInstance
import com.example.weatherapp.data.remote.WeatherResponse
import com.example.weatherapp.util.Constants
import retrofit2.Response

class WeatherRepository {

    suspend fun getWeather(city: String): Response<WeatherResponse> {
        return RetrofitInstance.api.getWeatherByCity(
            city = city,
            apiKey = Constants.API_KEY
        )
    }
}