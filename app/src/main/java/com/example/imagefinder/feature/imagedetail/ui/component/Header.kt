package com.example.imagefinder.feature.imagedetail.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.component.NetworkImage
import com.example.imagefinder.R
import com.example.common.ui.theme.ImageFinderTheme
import com.example.common.ui.theme.Space

@Composable
fun ImageDetailHeader(
    id: Long,
    imageUrl: String,
    title: String,
    isBookmarked: Boolean,
    onBookmarkClicked: (Long, Boolean) -> Unit
) {
    Column {
        NetworkImage(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.large)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            imageUrl = imageUrl,
            contentDescription = title,
            placeholder = painterResource(id = R.drawable.img_placeholder_view)
        )

        Spacer(modifier = Modifier.height(Space.s4))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h1
            )

            Icon(
                if (isBookmarked)
                    Icons.Filled.ThumbUp
                else Icons.Outlined.ThumbUp,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .clickable {
                        onBookmarkClicked.invoke(id, !isBookmarked)
                    },
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    ImageFinderTheme {
        ImageDetailHeader(
            id = 123,
            imageUrl = "/image",
            "title",
            isBookmarked = true,
        ) { id, isBookmarked ->

        }
    }
}
