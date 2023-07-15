@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.ModifierConfigCompose

@Composable
fun WidgetsBox() {
    ModifierConfigCompose("Box") { modifier ->
        Box(modifier.background(Color.Red)) {
            Text(text = "Hello", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
fun PreviewBox() {
    JetpackComposeCatalogTheme {
        WidgetsBox()
    }
}