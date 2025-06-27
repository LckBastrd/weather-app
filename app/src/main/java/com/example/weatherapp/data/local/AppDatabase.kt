package com.example.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedCity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedCityDao(): SavedCityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "weather_db"
                )
                    .fallbackToDestructiveMigration() // очищает и пересоздаёт БД при изменении схемы
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}