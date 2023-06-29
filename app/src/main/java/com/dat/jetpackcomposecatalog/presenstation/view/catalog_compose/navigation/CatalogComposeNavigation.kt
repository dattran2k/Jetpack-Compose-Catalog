package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.CatalogScreen
import com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose.box.BoxComposeRoute


fun NavGraphBuilder.catalogScreen(catalogScreen: CatalogScreen) {
    composable(
        route = catalogScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = catalogScreen.deepLink },
        ),
    ) {
        when (catalogScreen) {
            CatalogScreen.BoxScreen -> BoxComposeRoute()
        }
    }
}