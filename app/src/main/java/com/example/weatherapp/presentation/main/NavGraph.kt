package com.example.weatherapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.presentation.detail.SavedCityScreen
import com.example.weatherapp.data.local.SavedCity

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "main",
    lastSavedCity: String,
    cityList: List<SavedCity>,
    onCitySaved: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("main") {
            MainScreen(
                lastSavedCity = lastSavedCity,
                onNavigateToSaved = { navController.navigate("saved") },
                onCitySaved = onCitySaved
            )
        }
        composable("saved") {
            SavedCityScreen(
                cities = cityList,
                navController = navController
            )
        }
    }
}