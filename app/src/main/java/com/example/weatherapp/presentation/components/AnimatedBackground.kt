package com.example.weatherapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedBackground(condition: String) {
    val gradient = when (condition.lowercase()) {
        "clear" -> Brush.verticalGradient(colors = listOf(Color(0xFFFFE57F), Color(0xFFFFA000)))
        "rain", "drizzle" -> Brush.verticalGradient(colors = listOf(Color.Gray, Color.DarkGray))
        "snow" -> Brush.verticalGradient(colors = listOf(Color.White, Color.LightGray))
        "clouds" -> Brush.verticalGradient(colors = listOf(Color.LightGray, Color.Gray))
        else -> Brush.verticalGradient(colors = listOf(Color(0xFFE1F5FE), Color(0xFF81D4FA)))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    )
}