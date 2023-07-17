@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme

@Composable
fun AnimationShowCase(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        item {
            AnimationValueScreen()
        }
        item {
            AnimationContentSizeScreen()
        }
        item {
            AnimationContentScreen(Modifier.height(200.dp))
        }
        item {
            AnimationVisibilityScreen()
        }
    }
}

@Preview
@Composable
fun AnimationShowCasePreview() {
    JetpackComposeCatalogTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            AnimationShowCase(Modifier)
        }
    }
}