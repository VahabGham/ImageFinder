package com.example.imagefinder.data.network

import com.example.imagefinder.BuildConfig
import com.example.imagefinder.data.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.random.Random

interface ImageFinderApiService {

    /*
        I just passed the random value like Car (cause cars are cool)
        to actually have some api call to make it usable for my case in this sample app.
        otherwise this api suits for search feature
     */
    @GET("api")
    suspend fun findImage(
        @Query("q") query: String = "Car",
        @Query("key") key: String = BuildConfig.API_KEY
    ): ImagesResponse
}
