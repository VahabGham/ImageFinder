package com.example.imagefinder.feature.imagedetail.ui.model

data class ImageDetailUiState(
    val id: Long,
    val tags: List<String>,
    val userName: String,
    val image: String,
    val downloadsCount: Int,
    val likesCount: Int,
    val isBookmarked: Boolean
)
