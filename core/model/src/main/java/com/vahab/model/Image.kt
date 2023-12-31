package com.vahab.model

data class Image(
    val id: Long,
    val tags: List<String>,
    val previewURL: String,
    val largeImageURL: String,
    val downloadsCount: Int,
    val likesCount: Int,
    val views: Int,
    val userName: String,
    val userImageURL: String,
    val isBookmarked: Boolean,
)