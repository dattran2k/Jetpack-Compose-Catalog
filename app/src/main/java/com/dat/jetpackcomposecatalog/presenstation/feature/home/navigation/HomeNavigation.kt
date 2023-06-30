package com.dat.jetpackcomposecatalog.presenstation.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen

import com.dat.jetpackcomposecatalog.presenstation.feature.home.HomeRoute



fun NavGraphBuilder.homeScreen(onNavigateDetail: (todo: TodoItem) -> Unit) {
    composable(
        route = Screen.HomeScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.MainScreen.deepLink },
        ),
    ) {
        HomeRoute(onNavigateDetail)
    }
}
