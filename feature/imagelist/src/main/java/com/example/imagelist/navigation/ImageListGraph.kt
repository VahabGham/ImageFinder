package com.example.imagelist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.imagelist.ui.ImageListScreen
import com.example.navigation.route.ImageDetailDestination
import com.example.navigation.route.ImageListDestination

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