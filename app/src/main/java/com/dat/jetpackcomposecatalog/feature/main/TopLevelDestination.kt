
package com.dat.jetpackcomposecatalog.feature.main

import com.dat.jetpackcomposecatalog.common.Icon
import com.dat.jetpackcomposecatalog.common.Icon.DrawableResourceIcon
import com.dat.jetpackcomposecatalog.common.MyIcons
import com.dat.jetpackcomposecatalog.navigation.Screen


/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val route: String
) {
    CatalogOverview(
        selectedIcon = DrawableResourceIcon(MyIcons.MenuBook),
        unselectedIcon = DrawableResourceIcon(MyIcons.MenuBookBorder),
        route = Screen.CatalogOverviewScreen.route
    ),
    AnimationShowCase(
        selectedIcon = DrawableResourceIcon(MyIcons.Animation),
        unselectedIcon = DrawableResourceIcon(MyIcons.Animation),
        route = Screen.AnimationShowCase.route
    ),
    Info(
        selectedIcon = DrawableResourceIcon(MyIcons.Info),
        unselectedIcon = DrawableResourceIcon(MyIcons.InfoBorder),
        route = Screen.InfoScreen.route
    ),

}
