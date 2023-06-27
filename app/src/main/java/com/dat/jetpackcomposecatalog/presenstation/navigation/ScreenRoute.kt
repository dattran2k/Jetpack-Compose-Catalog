package com.dat.jetpackcomposecatalog.presenstation.navigation

import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_ARG_ID
import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_ARG_TITLE
import com.dat.jetpackcomposecatalog.presenstation.view.detail.DETAIL_NAME

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

abstract class ScreenRoute(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route
}

object CatalogScreenRote : ScreenRoute("Catalog")
object HomeScreenRote : ScreenRoute("Home")
object UserScreenRoute : ScreenRoute("User")

object DetailScreenRoute : ScreenRoute("$DETAIL_NAME/{$DETAIL_ARG_ID}/{$DETAIL_ARG_TITLE}") {
    data class DetailScreenArg(val id: Int, val title: String)

}