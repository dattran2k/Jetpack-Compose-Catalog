@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.ui.feature.catalog_compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.dat.ui.feature.CatalogComposeEnum
import com.dat.ui.feature.catalog_compose.animation.AnimatedContentRoute
import com.dat.ui.feature.catalog_compose.animation.AnimatedVisibilityRoute
import com.dat.ui.feature.catalog_compose.animation.AnimationContentSizeRoute
import com.dat.ui.feature.catalog_compose.animation.AnimationOffsetBouncingBallRoute
import com.dat.ui.feature.catalog_compose.animation.AnimationShowCaseRoute
import com.dat.ui.feature.catalog_compose.animation.AnimationValueRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutColumnLazyRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutColumnRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutGridLazyHorizontalRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutGridLazyHorizontalStaggeredRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutGridLazyVerticalRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutGridLazyVerticalStaggeredRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutRowLazyRoute
import com.dat.ui.feature.catalog_compose.layout.LayoutRowRoute
import com.dat.ui.feature.catalog_compose.modifier.ModifierConfigRoute
import com.dat.ui.navigation.Screen

const val CATALOG_ROUTE_NAME = "CatalogCompose"
const val CATALOG_ARG_ID = "CATALOG_ARG_ID"


fun NavController.navigateCatalogScreen(
    catalogComposeEnum: CatalogComposeEnum,
    navOptions: NavOptions? = null,
) {
    this.navigate("$CATALOG_ROUTE_NAME/${catalogComposeEnum.name}", navOptions)
}

fun NavController.navigateAnimationShowCase(
    navOptions: NavOptions? = null,
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
        AnimationShowCaseRoute()
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
            CatalogComposeHost(
                modifier = Modifier.padding(paddingValues),
                id = CatalogComposeEnum.valueOf(id)
            )
        }
    }
}

@Composable
private fun TopAppBar(
    title: String,
    navigateBack: (() -> Unit)?,
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

@Composable
fun CatalogComposeHost(modifier: Modifier, id: CatalogComposeEnum) {
    Surface(modifier = modifier) {
        when (id) {
//                    CatalogComposeEnum.Box -> BoxComposeScreen()
            CatalogComposeEnum.Column -> LayoutColumnRoute()
            CatalogComposeEnum.LazyColumn -> LayoutColumnLazyRoute()
            CatalogComposeEnum.Row -> LayoutRowRoute()
            CatalogComposeEnum.LazyRow -> LayoutRowLazyRoute()
            CatalogComposeEnum.LazyVerticalStaggeredGrid -> LayoutGridLazyVerticalStaggeredRoute()
            CatalogComposeEnum.LazyVerticalGrid -> LayoutGridLazyVerticalRoute()
            CatalogComposeEnum.LazyHorizontalStaggeredGrid -> LayoutGridLazyHorizontalStaggeredRoute()
            CatalogComposeEnum.LazyHorizontalGrid -> LayoutGridLazyHorizontalRoute()
            CatalogComposeEnum.ContentVisibility -> AnimatedVisibilityRoute()
            CatalogComposeEnum.AnimateContentSize -> AnimationContentSizeRoute()
            CatalogComposeEnum.AnimatedContent -> AnimatedContentRoute()
            CatalogComposeEnum.AnimatedValue -> AnimationValueRoute()
            CatalogComposeEnum.AnimationOffsetBouncingBall -> AnimationOffsetBouncingBallRoute()
            CatalogComposeEnum.AnimationShowCase -> AnimationShowCaseRoute()
            CatalogComposeEnum.Modifier -> ModifierConfigRoute()
        }
    }
}

