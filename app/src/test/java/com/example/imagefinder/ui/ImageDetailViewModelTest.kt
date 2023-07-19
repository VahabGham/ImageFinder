package com.example.imagefinder.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.example.imagefinder.data.ImageRepository
import com.example.imagefinder.data.asEntity
import com.example.imagefinder.data.asExternal
import com.example.imagefinder.data.imageData
import com.example.imagefinder.feature.imagedetail.ui.ImageDetailViewModel
import com.example.imagefinder.feature.route.ImageDetailDestination
import com.example.imagefinder.util.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ImageDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val imageId = 1L
    private val savedStateHandle = SavedStateHandle(
        mapOf(
            ImageDetailDestination.idArg to imageId
        )
    )

    private lateinit var viewModel: ImageDetailViewModel
    private lateinit var imageRepository: ImageRepository


    @Before
    fun setup() {
        imageRepository = mockk {
            coEvery { getImage(id = imageId) } returns flowOf(
                imageData.find { it.id.toLong() == imageId }!!
                    .asEntity().asExternal()
            )
        }
        viewModel = ImageDetailViewModel(savedStateHandle, imageRepository)
    }

    @Test
    fun testImageFromNavArgRepoId() = runTest {
        viewModel.state.test {
            val item = awaitItem()

            assert(item?.userName == imageData[1].user)
            assert(item?.userName != imageData[0].user)
        }

        coVerify { imageRepository.getImage(id = imageId) }
    }

    @After
    fun after() {
        clearAllMocks()
    }

}
