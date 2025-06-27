package com.example.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: SavedCity)

    @Query("SELECT * FROM SavedCity ORDER BY id DESC")
    fun getAll(): Flow<List<SavedCity>>
}
