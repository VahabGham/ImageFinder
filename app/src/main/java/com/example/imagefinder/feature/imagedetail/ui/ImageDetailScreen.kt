package com.example.imagefinder.feature.imagedetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagefinder.R
import com.example.common.ui.component.toolbar.ToolBar
import com.example.common.ui.component.toolbar.ToolbarActionModel
import com.example.common.ui.theme.ImageFinderTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.common.ui.theme.Space
import com.example.imagefinder.feature.imagedetail.ui.component.ImageDetailBody
import com.example.imagefinder.feature.imagedetail.ui.component.ImageDetailHeader


@Composable
internal fun ImageDetailScreen(
    viewModel: ImageDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.image_detail_title),
                navActionModel = ToolbarActionModel.Back(
                    caption = stringResource(id = R.string.simple_back_caption),
                    onClick = onBackClick
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = Space.s3, vertical = Space.s4)
                .verticalScroll(rememberScrollState())
        ) {

            state?.let { imageState ->

                ImageDetailHeader(
                    id = imageState.id,
                    imageUrl = imageState.image,
                    title = imageState.userName,
                    isBookmarked = imageState.isBookmarked,
                    onBookmarkClicked = { id,isBookmarked->
                        if (isBookmarked)
                            viewModel.bookmark(id)
                        else
                            viewModel.unBookmark(id)
                    }
                )

                Spacer(modifier = Modifier.height(Space.s5))

                ImageDetailBody(
                    downloadsCount = imageState.downloadsCount,
                    likesCount = imageState.likesCount,
                    tags = imageState.tags
                )

            } ?: run {
                Text(text = "There is no data at the moment")
            }

        }
    }
}

@Preview
@Composable
private fun Preview() {
    ImageFinderTheme() {
        ImageDetailScreen(
            onBackClick = {}
        )
    }
}
