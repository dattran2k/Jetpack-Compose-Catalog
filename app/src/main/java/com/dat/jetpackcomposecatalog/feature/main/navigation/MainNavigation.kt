package com.dat.jetpackcomposecatalog.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.feature.main.MainScreen
import com.dat.jetpackcomposecatalog.navigation.Screen


fun NavController.navigateMain(navOptions: NavOptions? = null) {
    this.navigate(Screen.MainScreen.route, navOptions)
}

fun NavGraphBuilder.mainScreen(navigateCatalogDetail: (com.dat.jetpackcomposecatalog.feature.CatalogComposeEnum) -> Unit) {
    composable(
        route = Screen.MainScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MainScreen.deepLink },
        ),
    ) {
        MainScreen(navigateCatalogDetail)
    }
}
