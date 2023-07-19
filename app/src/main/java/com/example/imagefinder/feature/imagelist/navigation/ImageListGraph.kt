package com.example.imagefinder.feature.imagelist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.imagefinder.feature.imagelist.ui.ImageListScreen
import com.example.imagefinder.feature.route.ImageDetailDestination
import com.example.imagefinder.feature.route.ImageListDestination

fun NavGraphBuilder.imageListGraph(
    navController: NavHostController
) {
    composable(ImageListDestination.route) {
        ImageListScreen(
            onItemClick = { id ->
                navController.navigate("${ImageDetailDestination.route}/$id")
            }
        )
    }
}