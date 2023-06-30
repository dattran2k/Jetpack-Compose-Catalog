package com.dat.jetpackcomposecatalog.presenstation.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen



fun NavController.navigateMain(navOptions: NavOptions? = null) {
    this.navigate(Screen.MenuScreen.route,navOptions)
}

fun NavGraphBuilder.mainScreen(navigateCatalogDetail: (CatalogComposeEnum) -> Unit) {
    composable(
        route = Screen.MainScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MainScreen.deepLink },
        ),
    ) {

        MainScreen(navigateCatalogDetail)
    }
}
