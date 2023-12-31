package com.dat.ui.feature.catalog_overview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.ui.feature.CatalogComposeEnum
import com.dat.ui.feature.catalog_overview.CatalogOverviewRoute
import com.dat.ui.navigation.Screen

fun NavController.navigateCatalogOverview(navOptions: NavOptions? = null) {
    this.navigate(Screen.CatalogOverviewScreen.route, navOptions)
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