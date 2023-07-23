@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.dat.ui.feature.catalog_compose.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.dat.ui.feature.catalog_compose.AnimamatedVisibilityScreen
import com.dat.ui.feature.catalog_compose.AnimatedContentScreen
import com.dat.ui.feature.catalog_compose.AnimationContentSizeScreen
import com.dat.ui.feature.catalog_compose.AnimationOffsetBouncingBall
import com.dat.ui.feature.catalog_compose.AnimationShowCase
import com.dat.ui.feature.catalog_compose.AnimationValueScreen
import com.dat.ui.feature.catalog_compose.LayoutColumn
import com.dat.ui.feature.catalog_compose.LayoutColumnLazy
import com.dat.ui.feature.catalog_compose.LayoutGridLazyHorizontal
import com.dat.ui.feature.catalog_compose.LayoutGridLazyHorizontalStaggered
import com.dat.ui.feature.catalog_compose.LayoutGridLazyVertical
import com.dat.ui.feature.catalog_compose.LayoutGridLazyVerticalStaggered
import com.dat.ui.feature.catalog_compose.LayoutLazyRow
import com.dat.ui.feature.catalog_compose.LayoutRow
import com.dat.ui.navigation.Screen

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

@Composable
fun CatalogComposeHost(modifier: Modifier, id: CatalogComposeEnum) {
    val modifierScrollable = Modifier.verticalScroll(rememberScrollState())
    Surface(modifier = modifier) {
        when (id) {
//                    CatalogComposeEnum.Box -> BoxComposeScreen()
            CatalogComposeEnum.Column -> LayoutColumn()
            CatalogComposeEnum.LazyColumn -> LayoutColumnLazy()
            CatalogComposeEnum.Row -> LayoutRow()
            CatalogComposeEnum.LazyRow -> LayoutLazyRow()
            CatalogComposeEnum.LazyVerticalStaggeredGrid -> LayoutGridLazyVerticalStaggered()
            CatalogComposeEnum.LazyVerticalGrid -> LayoutGridLazyVertical()
            CatalogComposeEnum.LazyHorizontalStaggeredGrid -> LayoutGridLazyHorizontalStaggered()
            CatalogComposeEnum.LazyHorizontalGrid -> LayoutGridLazyHorizontal()
            CatalogComposeEnum.ContentVisibility -> AnimamatedVisibilityScreen(modifierScrollable)
            CatalogComposeEnum.AnimateContentSize -> AnimationContentSizeScreen(modifierScrollable)
            CatalogComposeEnum.AnimatedContent -> AnimatedContentScreen()
            CatalogComposeEnum.AnimatedValue -> AnimationValueScreen(modifierScrollable)
            CatalogComposeEnum.AnimationOffsetBouncingBall -> AnimationOffsetBouncingBall()
            CatalogComposeEnum.AnimationShowCase -> AnimationShowCase()
        }
    }
}

