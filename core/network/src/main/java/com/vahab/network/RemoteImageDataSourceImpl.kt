package com.vahab.network


import com.vahab.common.network.safeApiCall
import com.vahab.network.apiservice.ImageFinderApiService
import com.vahab.network.model.ImagesResponse
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