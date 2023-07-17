
package com.dat.jetpackcomposecatalog.presentation.feature.main

import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon
import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon.DrawableResourceIcon
import com.dat.jetpackcomposecatalog.core.designsystem.icon.MyIcons
import com.dat.jetpackcomposecatalog.presentation.navigation.Screen


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
