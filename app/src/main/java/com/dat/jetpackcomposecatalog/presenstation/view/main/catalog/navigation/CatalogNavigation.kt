package com.dat.jetpackcomposecatalog.presenstation.view.main.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.MainScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.catalog.CatalogOverviewRoute

object CatalogOverviewScreenRoute : ScreenRoute("CatalogOverviewS")

fun NavGraphBuilder.catalogScreen() {
    composable(
        route = CatalogOverviewScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        CatalogOverviewRoute()
    }
}