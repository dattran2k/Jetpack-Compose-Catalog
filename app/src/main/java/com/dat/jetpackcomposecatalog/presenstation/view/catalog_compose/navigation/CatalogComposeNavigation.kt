package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose.box.BoxComposeRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.MainScreenRoute

sealed class CatalogScreenRoute(route: String) : ScreenRoute(route)
object BoxScreenRoute : CatalogScreenRoute("Box")

fun NavGraphBuilder.catalogScreen(catalogScreenRoute: CatalogScreenRoute) {
    composable(
        route = catalogScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        when (catalogScreenRoute) {
            BoxScreenRoute -> BoxComposeRoute()
        }
    }
}