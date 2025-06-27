package com.example.weatherapp.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.presentation.components.AnimatedBackground
import com.example.weatherapp.presentation.components.WeatherCard

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onNavigateToSaved: () -> Unit,
    onCitySaved: (String) -> Unit,
    lastSavedCity: String
) {
    val cityInput = remember { mutableStateOf(TextFieldValue(lastSavedCity)) }

    val weather by viewModel.weather.collectAsState()
    val error by viewModel.error.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedBackground(
            condition = weather?.weather?.firstOrNull()?.main ?: "-"
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            weather?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    WeatherCard(weather = it)
                }
            }

            OutlinedTextField(
                value = cityInput.value,
                onValueChange = { cityInput.value = it },
                label = { Text("Enter city name") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel.fetchWeather(cityInput.value.text)
                        onCitySaved(cityInput.value.text)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .padding(end = 8.dp)
                ) {
                    Text("Get weather", fontSize = 18.sp)
                }

                Button(
                    onClick = onNavigateToSaved,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .padding(start = 8.dp)
                ) {
                    Text("History", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            }

            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}