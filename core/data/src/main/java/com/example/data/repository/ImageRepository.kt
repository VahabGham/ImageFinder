package com.example.data.repository

import com.example.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun update()
    suspend fun getImages(): Flow<List<Image>>
    suspend fun getImage(id: Long): Flow<Image>
    suspend fun bookmark(id: Long)
    suspend fun unBookmark(id: Long)
}