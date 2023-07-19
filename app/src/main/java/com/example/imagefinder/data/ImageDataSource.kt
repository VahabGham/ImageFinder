package com.example.imagefinder.data


interface ImageDataSource {
    suspend fun getImages() : ImagesResponse
}