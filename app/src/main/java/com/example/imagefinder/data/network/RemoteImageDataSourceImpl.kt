package com.example.imagefinder.data.network

import com.example.imagefinder.common.network.safeApiCall
import com.example.imagefinder.data.ImageDataSource
import com.example.imagefinder.data.ImagesResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteImageDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val imageFinderApiService: ImageFinderApiService
) : ImageDataSource {

    override suspend fun getImages(): ImagesResponse =
        safeApiCall(dispatcher) {
            imageFinderApiService.findImage()
        }

}