package com.dat.jetpackcomposecatalog.presentation.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presentation.feature.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presentation.feature.main.MainScreen
import com.dat.jetpackcomposecatalog.presentation.navigation.Screen



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
