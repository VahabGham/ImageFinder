package com.vahab.data.repository

import com.vahab.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun update()
    suspend fun getImages(): Flow<List<Image>>
    suspend fun getImage(id: Long): Flow<Image>
    suspend fun bookmark(id: Long)
    suspend fun unBookmark(id: Long)
}