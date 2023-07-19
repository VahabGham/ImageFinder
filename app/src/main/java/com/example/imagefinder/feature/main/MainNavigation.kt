package com.example.imagefinder.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.imagefinder.feature.imagedetail.navigation.imageDetailGraph
import com.example.imagefinder.feature.imagelist.navigation.imageListGraph
import com.example.imagefinder.feature.route.ImageListDestination

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