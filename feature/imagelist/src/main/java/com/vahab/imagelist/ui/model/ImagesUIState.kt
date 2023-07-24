package com.vahab.imagelist.ui.model

import androidx.annotation.StringRes


data class ImageListUiState(
    val isLoading : Boolean = true,
    val images: List<ImageUiState>? = null,
    val error: ErrorUiState? = null
)

data class ImageUiState(
    val id: Long,
    val views: Int,
    val userName: String,
    val previewURL: String,
    val isBookmarked: Boolean,
)

data class ErrorUiState(@StringRes val data: Int)

