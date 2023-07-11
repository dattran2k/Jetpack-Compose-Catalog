package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme

@Composable
fun AnimationContentSize() {
    val message by remember { mutableStateOf("Hello") }
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .animateContentSize()
    ) {

    }
}

@Preview
@Composable
fun AnimationContentSizePreview() {
    JetpackComposeCatalogTheme {
        AnimationContentSize()
    }
}