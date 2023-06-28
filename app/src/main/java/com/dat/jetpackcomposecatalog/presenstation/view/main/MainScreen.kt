package com.dat.jetpackcomposecatalog.presenstation.view.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dat.jetpackcomposecatalog.R
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.presenstation.navigation.CatalogScreenRote
import com.dat.jetpackcomposecatalog.presenstation.navigation.HomeScreenRote
import com.dat.jetpackcomposecatalog.presenstation.navigation.ScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.navigation.UserScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.PrimaryColor
import com.dat.jetpackcomposecatalog.presenstation.view.main.catalog.CatalogRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.home.HomeRoute
import com.dat.jetpackcomposecatalog.presenstation.view.main.user.UserRoute

class MainItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val screenRoute: ScreenRoute
)

val tabItems = listOf(
    MainItem(R.drawable.ic_tab_home, R.string.tab_catalog, CatalogScreenRote),
    MainItem(R.drawable.ic_tab_home, R.string.tab_home, HomeScreenRote),
    MainItem(R.drawable.ic_tab_user, R.string.tab_user, UserScreenRoute)
)

@Composable
fun MainRoute(onNavigateDetail: (todo: TodoItem) -> Unit) {
    val navController = rememberNavController()
    MainScreen(navController, onNavigateDetail)
}

@Composable
fun MainScreen(
    navController: NavHostController,
    onNavigateDetail: (todo: TodoItem) -> Unit
) {
    Scaffold(
        backgroundColor = LocalCustomColorTheme.current.background,
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(screen.title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute.route } == true,
                        onClick = {
                            navController.navigate(screen.screenRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selectedContentColor = PrimaryColor,
                        unselectedContentColor = LocalCustomColorTheme.current.textTitle,
                        modifier = Modifier.background(LocalCustomColorTheme.current.background)
                    )

                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = HomeScreenRote.route,
            Modifier.padding(innerPadding)
        ) {
            tabItems.forEachIndexed { index, mainItem ->
                composable(
                    route = mainItem.screenRoute.route
                ) {
                    when (index) {
                        0 -> HomeRoute(onNavigateDetail)
                        1 -> CatalogRoute()
                        2 -> UserRoute()
                    }
                }
            }
        }
    }
}
