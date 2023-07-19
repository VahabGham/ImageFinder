package com.example.imagefinder.feature.imagedetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.imagefinder.feature.imagedetail.ui.ImageDetailScreen
import com.example.imagefinder.feature.route.ImageDetailDestination

fun NavGraphBuilder.imageDetailGraph(
    navController: NavHostController
) {
    composable(
        route = ImageDetailDestination.routeParams,
        arguments = ImageDetailDestination.arguments
    ) {
        ImageDetailScreen(
            onBackClick = navController::popBackStack
        )
    }
}
