package com.vahab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vahab.database.dao.ImageDao
import com.vahab.database.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}