package com.example.imagefinder.feature.route

import com.example.imagefinder.common.navigation.BaseDestination
import com.example.imagefinder.common.navigation.toRoute

private const val IMAGE_LIST_ROUTE = "imageList"

object ImageListDestination : BaseDestination() {
    override val route: String
        get() = toRoute(IMAGE_LIST_ROUTE)
}