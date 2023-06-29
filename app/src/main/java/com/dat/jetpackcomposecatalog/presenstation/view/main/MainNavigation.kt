package com.dat.jetpackcomposecatalog.presenstation.view.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen



//fun NavController.navigateMain() {
//    navigate(MainScreenRoute.route)
//}

fun NavGraphBuilder.mainScreen(
    onNavigateDetail: (todo : TodoItem) -> Unit
) {
    composable(
        route = Screen.MainScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MainScreen.deepLink },
        ),
    ) {
        MainRoute(onNavigateDetail)
    }
}
