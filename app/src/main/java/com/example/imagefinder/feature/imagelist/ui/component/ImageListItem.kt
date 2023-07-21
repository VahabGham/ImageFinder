package com.example.imagefinder.feature.imagelist.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.ui.component.NetworkImage
import com.example.imagefinder.R
import com.example.common.ui.component.TextIcon
import com.example.common.ui.theme.ImageFinderTheme
import com.example.common.ui.theme.Space

@Composable
fun ImageListItem(
    title: String,
    viewsCount: Int,
    imageUrl: String,
    isBookmarked: Boolean,
    onClick: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Space.s3, vertical = Space.s4)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.Start
        ) {

            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.FillWidth,
                imageUrl = imageUrl,
                contentDescription = title,
                placeholder = painterResource(id = R.drawable.img_placeholder_view)
            )

            Spacer(modifier = Modifier.height(Space.s4))

            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )

            Spacer(modifier = Modifier.height(Space.s3))

            TextIcon(
                icon = if (isBookmarked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                text = viewsCount.toString(),
                contentDescription = stringResource(id = R.string.cd_connection_view)
            )

        }

    }


}

@Preview
@Composable
private fun Preview() {
    ImageFinderTheme {
        ImageListItem(
            title = "Author",
            viewsCount = 12,
            imageUrl = "",
            isBookmarked = false
        ) {

        }
    }
}