package com.example.imagefinder.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.imagefinder.data.*
import com.example.imagefinder.data.database.AppDatabase
import com.example.imagefinder.data.database.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "image-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideImageDao(appDatabase: AppDatabase): ImageDao {
        return appDatabase.imageDao()
    }
}