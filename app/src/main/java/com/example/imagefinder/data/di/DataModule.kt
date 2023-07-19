package com.example.imagefinder.di

import com.example.imagefinder.data.*
import com.example.imagefinder.data.database.ImageDao
import com.example.imagefinder.data.network.RemoteImageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideRemoteImageDataSourceImpl(remoteImageDataSourceImpl: RemoteImageDataSourceImpl): ImageDataSource
}


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        imageDao: ImageDao,
        remoteImageDataSourceImpl: ImageDataSource,
    ): ImageRepository {
        return ImageRepositoryImpl(
            imageDao,
            remoteImageDataSourceImpl
        )
    }
}