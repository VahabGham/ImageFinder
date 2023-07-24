package com.example.imagefinder.ui

import app.cash.turbine.test
import com.example.common.exception.ConnectionError
import com.example.data.model.asEntity
import com.example.data.model.asExternal
import com.example.data.repository.ImageRepository
import com.example.imagefinder.R
import com.example.imagefinder.data.*
import com.example.imagefinder.util.MainDispatcherRule
import com.example.imagelist.ui.ImageListViewModel
import com.example.imagelist.ui.model.ImageListUiState
import com.example.imagelist.ui.model.ImageUiState
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val imageId = 0L

    private lateinit var viewModel: ImageListViewModel
    private lateinit var imageRepository: ImageRepository


    @Before
    fun setup() {

        imageRepository = mockk {
            coEvery { getImages() } returns flowOf(
                imageData.map {
                    it.asEntity().asExternal()
                }
            )
            coEvery { getImage(id = imageId) } returns flowOf(
                imageData.find { it.id.toLong() == imageId }!!
                    .asEntity().asExternal()
            )
        }

        viewModel = spyk(
            ImageListViewModel(
                imageRepository = imageRepository
            )
        )
    }


    @Test
    fun testFetchImageSuccess() = runTest {

        viewModel.fetchImages()

        coVerify { imageRepository.update() }
        verify {
            viewModel.stopLoading()
        }

    }

    @Test
    fun testFetchImageError() = runTest {
        coEvery { imageRepository.update() } throws ConnectionError()

        val job = launch {
            viewModel.state.test {
                val item = awaitItem()

                assert(item.error != null)
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.fetchImages()

        coVerify { imageRepository.update() }
        verify { viewModel.stopLoading() }
        verify {
            viewModel.loadFailed(R.string.general_connection_error)
        }

        job.apply {
            join()
            cancel()
        }
    }

    @Test
    fun testCollectImagesSuccess() = runTest {
        val expectedState = ImageListUiState(isLoading = false,
            images = imageData.map { image ->
            ImageUiState(
                id = image.id.toLong(),
                userName = image.user,
                views = image.views,
                previewURL = image.previewURL,
                isBookmarked = false
            )
        })

        viewModel.collectImages()

        viewModel.state.test {
            val item = awaitItem()
            assert(item == expectedState)
        }
    }

    @Test
    fun testCollectImageError() = runTest {
        coEvery { imageRepository.getImages() } throws ConnectionError()

        val job = launch {
            viewModel.state.test {
                val item = awaitItem()

                assert(item.error != null)
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.collectImages()

        coVerify { imageRepository.update() }
        verify {
            viewModel.loadFailed(R.string.general_connection_error)
        }

        job.apply {
            join()
            cancel()
        }
    }

    @After
    fun after() {
        clearAllMocks()
    }

}
