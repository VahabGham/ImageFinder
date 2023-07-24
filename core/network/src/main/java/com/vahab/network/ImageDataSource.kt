package com.vahab.network

import com.vahab.network.model.ImagesResponse


interface ImageDataSource {
    suspend fun getImages() : ImagesResponse
}