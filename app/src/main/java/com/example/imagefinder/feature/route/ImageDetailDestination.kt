package com.example.imagefinder.feature.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.imagefinder.common.navigation.BaseDestination
import com.example.imagefinder.common.navigation.toPathWithParameter
import com.example.imagefinder.common.navigation.toRoute

private const val IMAGE_DETAIL_ROUTE = "imageDetail"

object ImageDetailDestination : BaseDestination() {
    const val idArg = "id"

    override val route: String = toRoute(IMAGE_DETAIL_ROUTE)
    override val params: String
        get() = toPathWithParameter(idArg)

    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
}
