package com.dat.ui.feature.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink

import com.dat.ui.feature.menu.UserRoute
import com.dat.ui.navigation.Screen

fun NavController.navigateInfo(navOptions: NavOptions? = null) {
    this.navigate(Screen.InfoScreen.route, navOptions)
}

fun NavGraphBuilder.userScreen() {
    composable(
        route = Screen.InfoScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.InfoScreen.deepLink },
        ),
    ) {
        UserRoute()
    }
}
