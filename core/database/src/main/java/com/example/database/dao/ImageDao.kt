package com.example.database.dao

import androidx.room.*
import com.example.database.model.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM imageEntity")
    fun getAll(): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(item: ImageEntity)

    @Query("SELECT * FROM imageEntity WHERE id=:id")
    fun getImage(id: Long): Flow<ImageEntity>

    @Query("SELECT * FROM imageEntity WHERE id=:id")
    fun getImages(id: Long): List<ImageEntity>

    @Query("UPDATE imageEntity SET is_bookmarked = true WHERE id =:id")
    suspend fun bookmark(id: Long)

    @Query("UPDATE imageEntity SET is_bookmarked = false WHERE id =:id")
    suspend fun unBookmark(id: Long)

    @Query(
        "UPDATE imageEntity SET tags = :tags ," +
                "preview_url = :previewURL ," +
                "large_image_url = :largeImageURL ," +
                "downloads_count = :downloadsCount ," +
                "likes_count = :likesCount ," +
                "views = :views ," +
                "user_count = :userName ," +
                "user_image_url = :userImageURL " +
                " WHERE id =:id"
    )
    suspend fun updateImage(
        id: Long,
        tags: String,
        previewURL: String,
        largeImageURL: String,
        downloadsCount: Int,
        likesCount: Int,
        views: Int,
        userName: String,
        userImageURL: String
    )

    @Transaction
    suspend fun insertUpdateImages(images: List<ImageEntity>) {
        images.forEach { imageEntity ->
            val items = getImages(imageEntity.id)

            if (items.isEmpty())
                insertImage(imageEntity)
            else
                updateImage(
                    id = imageEntity.id,
                    tags = imageEntity.tags ?: "",
                    previewURL = imageEntity.previewURL ?: "",
                    largeImageURL = imageEntity.largeImageURL ?: "",
                    downloadsCount = imageEntity.downloadsCount ?: 0,
                    likesCount = imageEntity.likesCount ?: 0,
                    views = imageEntity.views ?: 0,
                    userName = imageEntity.userName ?: "",
                    userImageURL = imageEntity.userImageURL ?: ""
                )
        }
    }


}