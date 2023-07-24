package com.example.imagelistdetail.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.ui.component.TextIcon
import com.example.common.ui.theme.ImageFinderTheme
import com.example.common.ui.theme.Space
import com.example.common.R


@Composable
fun ImageDetailBody(
    downloadsCount: Int,
    likesCount: Int,
    tags: List<String>
) {
    Column {

        TextIcon(
            icon = Icons.Default.Share,
            text = stringResource(
                id = R.string.image_detail_downloaded,
                downloadsCount
            ),
            iconSize = Space.s4,
            contentDescription = stringResource(id = R.string.cd_connection_view)
        )

        Spacer(modifier = Modifier.height(Space.s4))

        TextIcon(
            icon = Icons.Default.ThumbUp,
            text = stringResource(
                id = R.string.image_detail_likes,
                likesCount
            ),
            iconSize = Space.s4,
            contentDescription = stringResource(id = R.string.cd_connection_view)
        )

        Spacer(modifier = Modifier.height(Space.s5))

        if (tags.isNotEmpty()) {

            Text(
                text = stringResource(id = R.string.image_detail_tags_title),
                style = MaterialTheme.typography.h2
            )

            Spacer(modifier = Modifier.height(Space.s3))

            Chips(tags = tags)
        }

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Chips(tags: List<String>) {

    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Space.s3)
    ) {
        tags.forEach {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(Space.s1),
            ) {
                Text(
                    text = "#${it.trim()}",
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = MaterialTheme.shapes.large
                        )
                        .wrapContentWidth()
                        .padding(Space.s3),
                    maxLines = 1,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }

}

@Preview
@Composable
private fun PreviewBody() {
    ImageFinderTheme {
        ImageDetailBody(
            tags = listOf("tag1","tag2","tag3"),
            downloadsCount = 10,
            likesCount = 12
        )
    }
}

@Preview
@Composable
private fun PreviewChips() {
    ImageFinderTheme {
        Chips(tags = listOf("tag1","tag2","tag3"))
    }
}
