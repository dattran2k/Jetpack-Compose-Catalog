package com.dat.jetpackcomposecatalog.presenstation.feature.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_overview.navigation.catalogOverviewScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_overview.navigation.navigateCatalogOverview
import com.dat.jetpackcomposecatalog.presenstation.feature.menu.navigation.navigateUser
import com.dat.jetpackcomposecatalog.presenstation.feature.menu.navigation.userScreen
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen
import timber.log.Timber

/**
 * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
 * route.
 */
val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()


@Composable
fun MainScreen(navigateCatalogDetail: (CatalogComposeEnum) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            MainBottomBar(topLevelDestinations, currentDestination) {
                navController.navigateToTopLevelDestination(it)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.CatalogOverviewScreen.route,
            Modifier.padding(innerPadding)
        ) {
            catalogOverviewScreen(navigateCatalogDetail)
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
    Timber.e(currentDestination.toString())
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
                    when (icon) {
                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )

                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )
                    }
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
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

    when (topLevelDestination) {
        TopLevelDestination.Catalog -> navigateCatalogOverview(topLevelNavOptions)
        TopLevelDestination.Menu -> navigateUser(topLevelNavOptions)
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false