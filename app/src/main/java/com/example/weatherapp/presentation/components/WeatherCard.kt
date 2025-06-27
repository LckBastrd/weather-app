package com.example.weatherapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.remote.WeatherResponse
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeatherCard(weather: WeatherResponse) {
    val condition = weather.weather.firstOrNull()?.description ?: "-"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F3F4).copy(alpha = 0.85f))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TemperatureDisplay(
                temp = weather.main.temp,
                condition = condition
            )
            Text("Humidity: ${weather.main.humidity}%")
            Text("Condition: $condition")
        }
    }
}