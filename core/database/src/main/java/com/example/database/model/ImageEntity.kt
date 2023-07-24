package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "preview_url") val previewURL: String?,
    @ColumnInfo(name = "large_image_url") val largeImageURL: String?,
    @ColumnInfo(name = "downloads_count") val downloadsCount: Int?,
    @ColumnInfo(name = "likes_count") val likesCount: Int?,
    @ColumnInfo(name = "views") val views: Int?,
    @ColumnInfo(name = "user_count") val userName: String?,
    @ColumnInfo(name = "user_image_url") val userImageURL: String?,
    @ColumnInfo(name = "is_bookmarked") val isBookmarked: Boolean?
)