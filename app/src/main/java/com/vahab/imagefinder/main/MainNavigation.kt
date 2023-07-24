package com.vahab.imagefinder.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vahab.imagelist.navigation.imageListGraph
import com.vahab.imagelistdetail.navigation.imageDetailGraph
import com.vahab.navigation.route.ImageListDestination

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ImageListDestination.route
    ) {
        imageListGraph(navController = navController)
        imageDetailGraph(navController = navController)
    }
}