package com.dat.jetpackcomposecatalog.presenstation.view.main.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen
import com.dat.jetpackcomposecatalog.presenstation.view.main.catalog.CatalogOverviewRoute


fun NavGraphBuilder.catalogScreen() {
    composable(
        route = Screen.CatalogOverviewScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.CatalogOverviewScreen.deepLink },
        ),
    ) {
        CatalogOverviewRoute()
    }
}