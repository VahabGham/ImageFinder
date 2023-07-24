package com.example.navigation.route

import com.example.navigation.route.navigation.BaseDestination
import com.example.navigation.route.navigation.toRoute

private const val IMAGE_LIST_ROUTE = "imageList"

object ImageListDestination : BaseDestination() {
    override val route: String
        get() = toRoute(IMAGE_LIST_ROUTE)
}