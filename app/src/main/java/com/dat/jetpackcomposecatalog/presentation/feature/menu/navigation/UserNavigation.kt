package com.dat.jetpackcomposecatalog.presentation.feature.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presentation.feature.menu.UserRoute
import com.dat.jetpackcomposecatalog.presentation.navigation.Screen

fun NavController.navigateUser(navOptions: NavOptions? = null) {
    this.navigate(Screen.MenuScreen.route,navOptions)
}

fun NavGraphBuilder.userScreen() {
    composable(
        route = Screen.MenuScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MenuScreen.deepLink },
        ),
    ) {
        UserRoute()
    }
}
