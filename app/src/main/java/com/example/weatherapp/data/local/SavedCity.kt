package com.example.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedCity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)