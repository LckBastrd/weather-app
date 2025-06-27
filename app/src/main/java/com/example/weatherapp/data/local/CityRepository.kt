package com.example.weatherapp.data.local

import kotlinx.coroutines.flow.Flow

class CityRepository(private val dao: SavedCityDao) {

    suspend fun insertCity(city: SavedCity) {
        dao.insert(city)
    }

    suspend fun saveCity(name: String) {
        dao.insert(SavedCity(name = name))
    }

    fun getAllCities(): Flow<List<SavedCity>> = dao.getAll()
}