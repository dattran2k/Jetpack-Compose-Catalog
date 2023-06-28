package com.dat.jetpackcomposecatalog.presenstation.view.main.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.MainScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.home.HomeRoute

object HomeScreenRoute : ScreenRoute("Home")

fun NavGraphBuilder.homeScreen(onNavigateDetail: (todo: TodoItem) -> Unit) {
    composable(
        route = HomeScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        HomeRoute(onNavigateDetail)
    }
}
