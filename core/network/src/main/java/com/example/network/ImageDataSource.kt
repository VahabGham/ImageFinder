package com.example.network

import com.example.network.model.ImagesResponse


interface ImageDataSource {
    suspend fun getImages() : ImagesResponse
}