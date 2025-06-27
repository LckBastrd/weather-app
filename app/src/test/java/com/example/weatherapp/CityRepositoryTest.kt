package com.example.weatherapp

import com.example.weatherapp.data.local.SavedCity
import com.example.weatherapp.data.local.SavedCityDao
import com.example.weatherapp.data.local.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeSavedCityDao : SavedCityDao {
    private val cities = mutableListOf<SavedCity>()

    override suspend fun insert(city: SavedCity) {
        cities.add(city)
    }

    override fun getAll(): Flow<List<SavedCity>> = flow {
        emit(cities.toList())
    }
}

class CityRepositoryTest {

    private lateinit var repository: CityRepository
    private lateinit var fakeDao: FakeSavedCityDao

    @Before
    fun setup() {
        fakeDao = FakeSavedCityDao()
        repository = CityRepository(fakeDao)
    }

    @Test
    fun insertCity_savesCity() = runBlocking {
        val city = SavedCity(name = "Barcelona")
        repository.insertCity(city)  // Убедись, что метод insertCity есть в CityRepository

        val result = fakeDao.getAll()
        var saved = listOf<SavedCity>()

        result.collect { list -> saved = list }

        assertEquals(1, saved.size)
        assertEquals("Barcelona", saved.first().name)
    }
}