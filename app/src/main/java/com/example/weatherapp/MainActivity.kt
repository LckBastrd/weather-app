package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.local.AppDatabase
import com.example.weatherapp.data.local.CityRepository
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.example.weatherapp.presentation.main.AppNavGraph
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getInstance(applicationContext)
        val repository = CityRepository(db.savedCityDao())

        enableEdgeToEdge()

        setContent {
            WeatherappTheme {
                val navController = rememberNavController()
                var savedCity by remember { mutableStateOf("") }
                val scope = rememberCoroutineScope()
                val cityList by repository.getAllCities().collectAsState(initial = emptyList())

                AppNavGraph(
                    navController = navController,
                    lastSavedCity = savedCity,
                    cityList = cityList,
                    onCitySaved = { city ->
                        savedCity = city
                        scope.launch {
                            repository.saveCity(city)
                        }
                    }
                )
            }
        }
    }
}