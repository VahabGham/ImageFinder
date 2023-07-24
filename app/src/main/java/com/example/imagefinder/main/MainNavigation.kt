package com.example.imagefinder.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.imagelist.navigation.imageListGraph
import com.example.imagelistdetail.navigation.imageDetailGraph
import com.example.navigation.route.ImageListDestination

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