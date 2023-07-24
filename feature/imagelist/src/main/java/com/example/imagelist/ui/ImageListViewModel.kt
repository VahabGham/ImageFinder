package com.example.imagelist.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.exception.ConnectionError
import com.example.data.repository.ImageRepository
import com.example.imagelist.ui.model.ImageListUiState
import com.example.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.common.R
import com.example.imagelist.ui.model.ErrorUiState
import com.example.imagelist.ui.model.ImageUiState


@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    val state: StateFlow<ImageListUiState> = MutableStateFlow(ImageListUiState())

    init {
        collectImages()
        fetchImages()
    }

    fun fetchImages() {
        startLoading()

        viewModelScope.launch {
            try {
                imageRepository.update()
            } catch (e: Exception) {
                stopLoading()
                handleError(e)
            }
        }
    }

    private fun handleError(e: Exception) {
        when (e) {
            is ConnectionError -> { // TODO : more specific Exception would be nice
                loadFailed(R.string.general_connection_error)
            }
            else -> loadFailed(R.string.general_generic_error)
        }
    }

    fun startLoading() {
        (state as MutableStateFlow).update {
            it.copy(isLoading = true, error = null)
        }
    }

    fun stopLoading() {
        (state as MutableStateFlow).update {
            it.copy(isLoading = false, error = null)
        }
    }

    fun loadFailed(@StringRes data: Int) {
        (state as MutableStateFlow).update {
            it.copy(isLoading = false, error = ErrorUiState(data))
        }
    }

    fun notShown() {
        (state as MutableStateFlow).update {
            it.copy(isLoading = false, images = emptyList(), error = null)
        }
    }

    fun shown(imageUiState: List<ImageUiState>) {
        (state as MutableStateFlow).update {
            it.copy(isLoading = false, images = imageUiState, error = null)
        }
    }

    fun collectImages() {
        viewModelScope.launch {
            try {
                imageRepository
                    .getImages()
                    .collect { images ->
                        if (images.isNotEmpty()) {
                            images.map { image ->
                                createImageUiState(image = image)
                            }.also { imageUiStates ->
                                shown(imageUiStates)
                            }
                        } else {
                            notShown()
                        }
                    }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun createImageUiState(image: Image): ImageUiState =
        ImageUiState(
            id = image.id,
            views = image.views,
            previewURL = image.previewURL,
            userName = image.userName,
            isBookmarked = image.isBookmarked
        )

}