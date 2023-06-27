package com.dat.jetpackcomposecatalog.presenstation.view.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute

const val MAIN_NAME = "Main"

object MainScreenRoute : ScreenRoute(MAIN_NAME)

//fun NavController.navigateMain() {
//    navigate(MainScreenRoute.route)
//}

fun NavGraphBuilder.mainScreen(
    onNavigateDetail: (todo : TodoItem) -> Unit
) {
    composable(
        route = MainScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        MainRoute(onNavigateDetail)
    }
}
