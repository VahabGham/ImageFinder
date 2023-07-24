package com.vahab.imagelistdetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.vahab.imagelistdetail.ui.ImageDetailScreen
import com.vahab.navigation.route.ImageDetailDestination

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
