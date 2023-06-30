package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.BoxComposeRoute
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.ColumnComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen

const val CATALOG_ROUTE_NAME = "CatalogCompose"
const val CATALOG_ARG_ID = "CATALOG_ARG_ID"


fun NavController.navigateCatalogScreen(catalogComposeEnum: CatalogComposeEnum) {
    this.navigate("$CATALOG_ROUTE_NAME/${catalogComposeEnum.name}")
}

fun NavGraphBuilder.catalogScreen() {
    composable(
        route = Screen.CatalogScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.CatalogScreen.deepLink },
        ),
        arguments = listOf(
            navArgument(CATALOG_ARG_ID) {
                type = NavType.StringType
            },
        )
    ) {
        val id = it.arguments?.getString(CATALOG_ARG_ID) ?: ""
        Box(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                when (CatalogComposeEnum.valueOf(id)) {
                    CatalogComposeEnum.Box -> BoxComposeRoute()
                    CatalogComposeEnum.Column -> ColumnComposeScreen()
                    CatalogComposeEnum.Row -> ColumnComposeScreen()
                    else -> {

                    }
                }
            }
        }
    }
}