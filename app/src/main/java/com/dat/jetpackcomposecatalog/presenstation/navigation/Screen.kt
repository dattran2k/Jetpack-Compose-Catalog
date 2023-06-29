package com.dat.jetpackcomposecatalog.presenstation.navigation

import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_ARG_ID
import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_ARG_TITLE
import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_NAME

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

sealed class Screen(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route

    object HomeScreen : Screen("Home")
    object UserScreen : Screen("User")
    object MainScreen : Screen("Main")
    object DetailScreen : Screen("$DETAIL_NAME/{$DETAIL_ARG_ID}/{$DETAIL_ARG_TITLE}") {
        data class DetailScreenArg(val id: Int, val title: String)
    }
    object CatalogOverviewScreen : Screen("CatalogOverview")
}


sealed class CatalogScreen(route: String) : Screen(route) {
    object BoxScreen : CatalogScreen("Box")
}