@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.ColumnComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.ColumnLazyComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.GridLazyHorizontalStaggeredComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.GridLazyVerticalStaggeredComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.LazyRowComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout.RowComposeScreen
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen

const val CATALOG_ROUTE_NAME = "CatalogCompose"
const val CATALOG_ARG_ID = "CATALOG_ARG_ID"


fun NavController.navigateCatalogScreen(catalogComposeEnum: CatalogComposeEnum) {
    this.navigate("$CATALOG_ROUTE_NAME/${catalogComposeEnum.name}")
}


fun NavGraphBuilder.catalogScreen(navigateBack: () -> Unit) {
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = id,
                            style = MaterialTheme.typography.titleMedium.copy(
                                MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    modifier = Modifier
                        .shadow(10.dp)
                )
            },
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                when (CatalogComposeEnum.valueOf(id)) {
//                    CatalogComposeEnum.Box -> BoxComposeScreen()
                    CatalogComposeEnum.Column -> ColumnComposeScreen()
                    CatalogComposeEnum.LazyColumn -> ColumnLazyComposeScreen()
                    CatalogComposeEnum.Row -> RowComposeScreen()
                    CatalogComposeEnum.LazyRow -> LazyRowComposeScreen()
                    CatalogComposeEnum.LazyVerticalStaggeredGrid -> GridLazyVerticalStaggeredComposeScreen()
                    CatalogComposeEnum.LazyHorizontalStaggeredGrid -> GridLazyHorizontalStaggeredComposeScreen()
                }
            }
        }
    }
}