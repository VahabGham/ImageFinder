package com.example.imagefinder.common.ui.component.toolbar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    navActionModel: ToolbarActionModel? = null
) {
    TopAppBar(
        title = {
            Text(text = title, color = White)
        },
        navigationIcon = {
            navActionModel?.let {
                IconButton(
                    onClick = navActionModel.onClick,
                ) {
                    Icon(
                        imageVector = navActionModel.icon,
                        contentDescription = navActionModel.caption,
                        tint = White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = androidx.compose.material.MaterialTheme.colors.primary
        )
    )
}
