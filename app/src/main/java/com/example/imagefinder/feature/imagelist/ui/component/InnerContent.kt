package com.example.imagefinder.feature.imagelist.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefinder.common.ui.theme.ImageFinderTheme
import com.example.imagefinder.common.ui.theme.Space
import com.example.imagefinder.feature.imagelist.ui.model.ImageUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InnerContent(
    images: List<ImageUiState>,
    onItemClick: (id: Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(Space.s3),
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = Space.s3,
        horizontalArrangement = Arrangement.spacedBy(Space.s2),
        content = {
            images.let { images ->
                items(images.size) { item ->
                    images[item].let { image ->
                        ImageListItem(
                            title = image.userName,
                            viewsCount = image.views,
                            imageUrl = image.previewURL,
                            isBookmarked = image.isBookmarked
                        ) {
                            onItemClick(image.id)
                        }
                    }
                }
            }

        }
    )
}

@Composable
@Preview
fun InnerContentPreview() {
    ImageFinderTheme {
        InnerContent(
            images = listOf(
                ImageUiState(
                    id = 1L,
                    views = 100,
                    userName = "Name",
                    previewURL = "/image1",
                    isBookmarked = false
                ),
                ImageUiState(
                    id = 2L,
                    views = 100,
                    userName = "Namm2",
                    previewURL = "/image2",
                    isBookmarked = true
                ),
                ImageUiState(
                    id = 3L,
                    views = 100,
                    userName = "Name",
                    previewURL = "/image3",
                    isBookmarked = false
                )
            )
        ) {

        }
    }
}