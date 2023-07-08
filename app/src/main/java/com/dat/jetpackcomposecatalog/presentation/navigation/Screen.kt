package com.dat.jetpackcomposecatalog.presentation.navigation

import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.navigation.CATALOG_ARG_ID
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.navigation.CATALOG_ROUTE_NAME
import com.dat.jetpackcomposecatalog.presentation.feature.main.TopLevelDestination

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

sealed class Screen(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route

    object MenuScreen : Screen(TopLevelDestination.Menu.name)
    object MainScreen : Screen("Main")
    object CatalogOverviewScreen : Screen(TopLevelDestination.Catalog.name)
    object CatalogScreen : Screen("$CATALOG_ROUTE_NAME/{$CATALOG_ARG_ID}")
}
