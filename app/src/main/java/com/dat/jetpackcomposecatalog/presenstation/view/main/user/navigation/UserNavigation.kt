package com.dat.jetpackcomposecatalog.presenstation.view.main.user.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.MainScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.user.UserRoute

object UserScreenRoute : ScreenRoute("User")

fun NavGraphBuilder.userScreen() {
    composable(
        route = UserScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        UserRoute()
    }
}
