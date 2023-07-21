package com.example.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    val hits: List<ImageResponse>
)

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val id: Int,
    val tags: String,
    val previewURL: String,
    val largeImageURL: String,
    val downloads: Int,
    val likes: Int,
    val views: Int,
    val user: String,
    val userImageURL: String
)


