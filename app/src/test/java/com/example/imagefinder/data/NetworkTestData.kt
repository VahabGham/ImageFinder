package com.example.imagefinder.data

import com.example.network.model.ImageResponse
import com.example.network.model.ImagesResponse
import io.mockk.every
import io.mockk.mockk

val imageData = listOf<ImageResponse>(
    mockk(relaxed = true) {
        every { id } returns 0
        every { user } returns "Image 0"
    },
    mockk(relaxed = true) {
        every { id } returns 1
        every { user } returns "Image 1"
    },
    mockk(relaxed = true) {
        every { id } returns 2
        every { user } returns "Image 2"
    },
    mockk(relaxed = true) {
        every { id } returns 3
        every { user } returns "Image 3"
    },
)

val imagesData = ImagesResponse(imageData)