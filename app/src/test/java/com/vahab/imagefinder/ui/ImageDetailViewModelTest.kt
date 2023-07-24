package com.vahab.imagefinder.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.vahab.data.model.asEntity
import com.vahab.data.model.asExternal
import com.vahab.data.repository.ImageRepository
import com.vahab.imagefinder.data.imageData
import com.vahab.imagelistdetail.ui.ImageDetailViewModel
import com.vahab.imagefinder.util.MainDispatcherRule
import com.vahab.navigation.route.ImageDetailDestination
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
