package com.example.weatherapp

import com.example.weatherapp.data.local.SavedCity
import org.junit.Assert.assertEquals
import org.junit.Test

class SavedCityModelTest {

    @Test
    fun savedCity_hasCorrectData() {
        val city = SavedCity(id = 1, name = "Madrid")

        assertEquals(1, city.id)
        assertEquals("Madrid", city.name)
    }
}