package com.example.imagefinder.network

import com.example.imagefinder.data.*
import com.example.imagefinder.data.network.ImageFinderApiService
import com.example.imagefinder.data.network.RemoteImageDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImageDataSourceTest {

    private lateinit var source: ImageDataSource

    private val testDispatcher = StandardTestDispatcher()

    private val imageFindApiService: ImageFinderApiService = mockk {
        coEvery {
            findImage()
        } returns imagesData
    }

    @Before
    fun setUp() {
        source = RemoteImageDataSourceImpl(
            dispatcher = testDispatcher,
            imageFinderApiService = imageFindApiService
        )
    }

    @Test
    fun testRemoteSourceReturnsExpectedData() = runTest(testDispatcher) {
        Assert.assertEquals(
            imagesData,
            source.getImages()
        )
    }
}
