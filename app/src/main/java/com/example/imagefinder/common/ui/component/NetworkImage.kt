package com.example.imagefinder.common.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier : Modifier,
    imageUrl: String,
    contentDescription: String,
    contentScale : ContentScale,
    placeholder: Painter?
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = placeholder,
        error = placeholder // TODO : nicer to have a different image
    )
}
