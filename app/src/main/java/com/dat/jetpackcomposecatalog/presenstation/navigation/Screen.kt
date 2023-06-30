package com.dat.jetpackcomposecatalog.presenstation.navigation

import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.navigation.CATALOG_ARG_ID
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.navigation.CATALOG_ROUTE_NAME
import com.dat.jetpackcomposecatalog.presenstation.feature.detail.DETAIL_ARG_ID
import com.dat.jetpackcomposecatalog.presenstation.feature.detail.DETAIL_ARG_TITLE
import com.dat.jetpackcomposecatalog.presenstation.feature.detail.DETAIL_NAME
import com.dat.jetpackcomposecatalog.presenstation.feature.main.TopLevelDestination

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

sealed class Screen(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route

    object HomeScreen : Screen("Home")
    object MenuScreen : Screen(TopLevelDestination.Menu.name)
    object MainScreen : Screen("Main")
    object DetailScreen : Screen("$DETAIL_NAME/{$DETAIL_ARG_ID}/{$DETAIL_ARG_TITLE}") {
        data class DetailScreenArg(val id: Int, val title: String)
    }

    object CatalogOverviewScreen : Screen(TopLevelDestination.Catalog.name)
    object CatalogScreen : Screen("$CATALOG_ROUTE_NAME/{$CATALOG_ARG_ID}")
}
