@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.ModifierConfigCompose

@Composable
fun BoxComposeScreen() {
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
        BoxComposeScreen()
    }
}