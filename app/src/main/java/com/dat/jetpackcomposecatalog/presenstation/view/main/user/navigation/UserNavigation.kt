package com.dat.jetpackcomposecatalog.presenstation.view.main.user.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen
import com.dat.jetpackcomposecatalog.presenstation.view.main.user.UserRoute


fun NavGraphBuilder.userScreen() {
    composable(
        route = Screen.UserScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MainScreen.deepLink },
        ),
    ) {
        UserRoute()
    }
}
