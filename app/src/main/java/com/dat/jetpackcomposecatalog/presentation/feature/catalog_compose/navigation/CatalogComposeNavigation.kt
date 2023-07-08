@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.dat.jetpackcomposecatalog.presentation.feature.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutColumn
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutColumnLazy
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutGridLazyHorizontal
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutGridLazyHorizontalStaggered
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutGridLazyVertical
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutGridLazyVerticalStaggered
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutLazyRow
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.LayoutRow
import com.dat.jetpackcomposecatalog.presentation.navigation.Screen

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
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                val fillModifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.secondary)
                when (CatalogComposeEnum.valueOf(id)) {
//                    CatalogComposeEnum.Box -> BoxComposeScreen()
                    CatalogComposeEnum.Column -> LayoutColumn(fillModifier)
                    CatalogComposeEnum.LazyColumn -> LayoutColumnLazy(fillModifier)
                    CatalogComposeEnum.Row -> LayoutRow(fillModifier)
                    CatalogComposeEnum.LazyRow -> LayoutLazyRow(fillModifier)
                    CatalogComposeEnum.LazyVerticalStaggeredGrid -> LayoutGridLazyVerticalStaggered(
                        fillModifier
                    )

                    CatalogComposeEnum.LazyVerticalGrid -> LayoutGridLazyVertical(
                        fillModifier
                    )

                    CatalogComposeEnum.LazyHorizontalStaggeredGrid -> LayoutGridLazyHorizontalStaggered(
                        fillModifier
                    )

                    CatalogComposeEnum.LazyHorizontalGrid -> LayoutGridLazyHorizontal(
                        fillModifier
                    )


                }
            }
        }
    }
}