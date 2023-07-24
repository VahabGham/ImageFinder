package com.example.network


import com.example.common.network.safeApiCall
import com.example.network.apiservice.ImageFinderApiService
import com.example.network.model.ImagesResponse
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