package com.example.common.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextIcon(
    icon: ImageVector,
    iconSize: Dp = 12.dp,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body2,
    contentDescription: String?
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            icon,
            modifier = Modifier
                .width(iconSize)
                .height(iconSize),
            contentDescription = contentDescription
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text,
            style = textStyle
        )
    }

}