package com.vahab.imagelistdetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahab.data.repository.ImageRepository
import com.vahab.imagelistdetail.ui.model.ImageDetailUiState

import com.vahab.model.Image
import com.vahab.navigation.route.ImageDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val imageRepository: ImageRepository
) : ViewModel() {

    fun bookmark(id: Long) {
        viewModelScope.launch {
            imageRepository.bookmark(id)
        }
    }

    fun unBookmark(id: Long) {
        viewModelScope.launch {
            imageRepository.unBookmark(id)
        }
    }

    private val repoId: Long = checkNotNull(savedStateHandle[ImageDetailDestination.idArg])

    val state: StateFlow<ImageDetailUiState?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            imageRepository.getImage(id = repoId).collectLatest { image ->
                (state as MutableStateFlow).update {
                    createImageDetailUiState(image)
                }
            }

        }
    }

    private fun createImageDetailUiState(image: Image) : ImageDetailUiState {
        return ImageDetailUiState(
            id = image.id,
            tags = image.tags,
            userName = image.userName,
            image = image.largeImageURL,
            downloadsCount = image.downloadsCount,
            likesCount = image.likesCount,
            isBookmarked = image.isBookmarked
        )
    }
}
