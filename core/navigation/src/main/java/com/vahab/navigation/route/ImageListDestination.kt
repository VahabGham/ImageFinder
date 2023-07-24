package com.vahab.navigation.route

import com.vahab.navigation.route.navigation.BaseDestination
import com.vahab.navigation.route.navigation.toRoute

private const val IMAGE_LIST_ROUTE = "imageList"

object ImageListDestination : BaseDestination() {
    override val route: String
        get() = toRoute(IMAGE_LIST_ROUTE)
}