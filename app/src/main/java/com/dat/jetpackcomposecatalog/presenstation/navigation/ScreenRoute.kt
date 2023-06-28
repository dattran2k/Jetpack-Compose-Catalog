package com.dat.jetpackcomposecatalog.presenstation.navigation

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

abstract class ScreenRoute(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route
}


