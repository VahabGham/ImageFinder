package com.vahab.data.repository

import com.vahab.data.model.asEntity
import com.vahab.data.model.asExternal
import com.vahab.database.dao.ImageDao
import com.vahab.model.Image
import com.vahab.network.ImageDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val remoteImageDataSourceImpl: ImageDataSource
) : ImageRepository {


    /*
        make an api call and update the local storage
     */
    override suspend fun update() {
        remoteImageDataSourceImpl.getImages().hits
            .map {
                it.asEntity()
            }.let {
                imageDao.insertUpdateImages(it)
            }
    }

    /*
        provides stream of images from local storage
     */
    override suspend fun getImages(): Flow<List<Image>> =
        imageDao.getAll().map {
            it.map {imageEntity->
                imageEntity.asExternal()
            }
        }

    /*
        provides an image by its ID
     */
    override suspend fun getImage(id: Long): Flow<Image> {
        return imageDao.getImage(id).map {
            it.asExternal()
        }
    }

    /*
        bookmarked an image
     */
    override suspend fun bookmark(id: Long) {
        imageDao.bookmark(id)
    }

    /*
        unbookmarked an image
     */
    override suspend fun unBookmark(id: Long) {
        imageDao.unBookmark(id)
    }

}