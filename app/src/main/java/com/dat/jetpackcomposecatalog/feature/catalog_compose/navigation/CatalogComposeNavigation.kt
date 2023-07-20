@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.feature.catalog_compose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dat.jetpackcomposecatalog.feature.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimamatedVisibilityScreen
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimatedContentScreen
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimationContentSizeScreen
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimationOffsetBouncingBall
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimationShowCase
import com.dat.jetpackcomposecatalog.feature.catalog_compose.animation.AnimationValueScreen
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutColumn
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutColumnLazy
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutGridLazyHorizontal
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutGridLazyHorizontalStaggered
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutGridLazyVertical
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutGridLazyVerticalStaggered
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutLazyRow
import com.dat.jetpackcomposecatalog.feature.catalog_compose.layout.LayoutRow
import com.dat.jetpackcomposecatalog.navigation.Screen

const val CATALOG_ROUTE_NAME = "CatalogCompose"
const val CATALOG_ARG_ID = "CATALOG_ARG_ID"


fun NavController.navigateCatalogScreen(
    catalogComposeEnum: CatalogComposeEnum,
    navOptions: NavOptions? = null
) {
    this.navigate("$CATALOG_ROUTE_NAME/${catalogComposeEnum.name}", navOptions)
}

fun NavController.navigateAnimationShowCase(
    navOptions: NavOptions? = null
) {
    this.navigate(Screen.AnimationShowCase.route, navOptions)
}

fun NavGraphBuilder.animationShowCaseHome() {
    composable(
        route = Screen.AnimationShowCase.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.AnimationShowCase.deepLink },
        ),
    ) {
        AnimationShowCase()
    }
}

fun NavGraphBuilder.catalogScreen(navigateBack: (() -> Unit)? = null) {
    composable(
        route = Screen.CatalogScreen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Screen.CatalogScreen.deepLink },
        ),
        arguments = listOf(
            navArgument(CATALOG_ARG_ID) {
                type = NavType.StringType
            },
        ),
    ) {
        val id = it.arguments?.getString(CATALOG_ARG_ID) ?: ""
        Scaffold(
            topBar = {
                navigateBack?.let {
                    TopAppBar(id, navigateBack)
                }
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
                when (CatalogComposeEnum.valueOf(id)) {
//                    CatalogComposeEnum.Box -> BoxComposeScreen()
                    CatalogComposeEnum.Column -> LayoutColumn(
                        fillModifier
                    )

                    CatalogComposeEnum.LazyColumn -> LayoutColumnLazy(
                        fillModifier
                    )

                    CatalogComposeEnum.Row -> LayoutRow(
                        fillModifier
                    )

                    CatalogComposeEnum.LazyRow -> LayoutLazyRow(
                        fillModifier
                    )

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

                    CatalogComposeEnum.ContentVisibility -> AnimamatedVisibilityScreen(
                        fillModifier.verticalScroll(rememberScrollState())
                    )

                    CatalogComposeEnum.AnimateContentSize -> AnimationContentSizeScreen(
                        fillModifier.verticalScroll(rememberScrollState())
                    )

                    CatalogComposeEnum.AnimatedContent -> AnimatedContentScreen(
                        fillModifier
                    )

                    CatalogComposeEnum.AnimatedValue -> AnimationValueScreen(
                        fillModifier.verticalScroll(rememberScrollState())
                    )

                    CatalogComposeEnum.AnimationOffsetBouncingBall -> AnimationOffsetBouncingBall(
                        fillModifier
                    )

                    CatalogComposeEnum.AnimationShowCase -> AnimationShowCase(
                        fillModifier
                    )
                }
            }
        }
    }
}

@Composable
private fun TopAppBar(
    title: String,
    navigateBack: (() -> Unit)?
) {
    TopAppBar(
        modifier = Modifier.shadow(10.dp),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        navigationIcon = {
            navigateBack?.let {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
    )
}