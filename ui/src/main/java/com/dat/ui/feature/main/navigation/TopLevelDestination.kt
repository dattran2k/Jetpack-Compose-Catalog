
package com.dat.ui.feature.main.navigation

import com.dat.designsystem.Icon
import com.dat.designsystem.MyIcons
import com.dat.ui.navigation.Screen


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
        selectedIcon = Icon.DrawableResourceIcon(MyIcons.MenuBook),
        unselectedIcon = Icon.DrawableResourceIcon(MyIcons.MenuBookBorder),
        route = Screen.CatalogOverviewScreen.route
    ),
    AnimationShowCase(
        selectedIcon = Icon.DrawableResourceIcon(MyIcons.Animation),
        unselectedIcon = Icon.DrawableResourceIcon(MyIcons.Animation),
        route = Screen.AnimationShowCase.route
    ),
    Info(
        selectedIcon = Icon.DrawableResourceIcon(MyIcons.Info),
        unselectedIcon = Icon.DrawableResourceIcon(MyIcons.InfoBorder),
        route = Screen.InfoScreen.route
    ),

}
