package com.vahab.data.di


import com.vahab.data.repository.ImageRepository
import com.vahab.data.repository.ImageRepositoryImpl
import com.vahab.database.dao.ImageDao
import com.vahab.network.ImageDataSource
import com.vahab.network.RemoteImageDataSourceImpl
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