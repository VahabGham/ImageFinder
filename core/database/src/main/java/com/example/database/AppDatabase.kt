package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.ImageDao
import com.example.database.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}