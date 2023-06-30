package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_overview.CatalogOverviewRoute
fun NavController.navigateCatalogOverview(navOptions: NavOptions? = null) {
    this.navigate(Screen.CatalogOverviewScreen.route,navOptions)
}


fun NavGraphBuilder.catalogOverviewScreen(navigateCatalogDetail: (CatalogComposeEnum) -> Unit) {
    composable(
        route = Screen.CatalogOverviewScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.CatalogOverviewScreen.deepLink },
        ),
    ) {
        CatalogOverviewRoute(navigateCatalogDetail)
    }
}