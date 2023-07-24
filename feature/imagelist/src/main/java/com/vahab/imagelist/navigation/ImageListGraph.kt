package com.vahab.imagelist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.vahab.imagelist.ui.ImageListScreen
import com.vahab.navigation.route.ImageDetailDestination
import com.vahab.navigation.route.ImageListDestination

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