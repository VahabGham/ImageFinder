package com.example.data.model

import com.example.database.model.ImageEntity
import com.example.model.Image
import com.example.network.model.ImageResponse


/*
    Entity postfix is representing DataModel for DB
 */
fun ImageResponse.asEntity() = ImageEntity(
    id = id.toLong(), // TODO : good to fix later
    tags = tags,
    previewURL = previewURL,
    largeImageURL = largeImageURL,
    downloadsCount = downloads,
    likesCount = likes,
    views = views,
    userName = user,
    userImageURL = userImageURL,
    isBookmarked = false
)

/*
    External postfix is representing DataModel for core business.
 */
fun ImageEntity.asExternal() = Image(
    id = id,
    tags = tags?.split(",") ?: emptyList(),
    previewURL = previewURL ?: "",
    largeImageURL = largeImageURL ?: "",
    downloadsCount = downloadsCount ?: 0,
    likesCount = likesCount ?: 0,
    views = views ?: 0,
    userName = userName ?: "",
    userImageURL = userImageURL ?: "",
    isBookmarked = isBookmarked ?: false
)
