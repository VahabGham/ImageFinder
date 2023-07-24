package com.example.data.di


import com.example.data.repository.ImageRepository
import com.example.data.repository.ImageRepositoryImpl
import com.example.database.dao.ImageDao
import com.example.network.ImageDataSource
import com.example.network.RemoteImageDataSourceImpl
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
object DataModule {

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