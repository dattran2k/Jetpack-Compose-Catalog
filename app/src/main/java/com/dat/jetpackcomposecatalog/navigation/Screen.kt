package com.dat.jetpackcomposecatalog.navigation

import com.dat.jetpackcomposecatalog.feature.catalog_compose.navigation.CATALOG_ARG_ID
import com.dat.jetpackcomposecatalog.feature.catalog_compose.navigation.CATALOG_ROUTE_NAME

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

sealed class Screen(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route

    object MainScreen : Screen("Main")
    object CatalogOverviewScreen : Screen("CatalogOverviewScreen")
    object InfoScreen : Screen("MenuScreen")
    object AnimationShowCase : Screen("AnimationShowCase")
    object CatalogScreen : Screen("$CATALOG_ROUTE_NAME/{$CATALOG_ARG_ID}")
}
