package com.dat.jetpackcomposecatalog.feature.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dat.jetpackcomposecatalog.feature.catalog_compose.navigation.animationShowCaseHome
import com.dat.jetpackcomposecatalog.feature.catalog_compose.navigation.navigateAnimationShowCase
import com.dat.jetpackcomposecatalog.feature.catalog_overview.navigation.catalogOverviewScreen
import com.dat.jetpackcomposecatalog.feature.catalog_overview.navigation.navigateCatalogOverview
import com.dat.jetpackcomposecatalog.feature.menu.navigation.navigateInfo
import com.dat.jetpackcomposecatalog.feature.menu.navigation.userScreen
import com.dat.jetpackcomposecatalog.widget.IconLoader

/**
 * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
 * route.
 */
val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

@Composable
fun MainScreen(navigateCatalogDetail: (com.dat.jetpackcomposecatalog.feature.CatalogComposeEnum) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            MainBottomBar(
                topLevelDestinations,
                currentDestination,
                navController::navigateToTopLevelDestination
            )
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = TopLevelDestination.CatalogOverview.route,
            Modifier.padding(innerPadding)
        ) {
            catalogOverviewScreen(navigateCatalogDetail)
            animationShowCaseHome()
            userScreen()
        }
    }
}

@Composable
private fun MainBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    BottomAppBar(
        modifier = Modifier
            .height(56.dp) // Set the desired height here
            .fillMaxWidth(),
    ) {
        destinations.forEach { destination ->
            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (isSelected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    IconLoader(icon = icon)
                })
        }
    }
}

fun NavHostController.navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reSelecting the same item
        launchSingleTop = true
        // Restore state when reSelecting a previously selected item
        restoreState = true
    }

    when (topLevelDestination.route) {
        TopLevelDestination.CatalogOverview.route -> navigateCatalogOverview(topLevelNavOptions)
        TopLevelDestination.Info.route -> navigateInfo(topLevelNavOptions)
        TopLevelDestination.AnimationShowCase.route -> navigateAnimationShowCase(topLevelNavOptions)
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false