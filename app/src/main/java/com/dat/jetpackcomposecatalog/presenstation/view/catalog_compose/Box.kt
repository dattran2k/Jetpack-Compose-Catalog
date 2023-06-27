@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.ModifierConfigCompose
// TODO I think it is not right time to do it
@Composable
fun BoxComposeRoute() {
    BoxComposeScreen()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxComposeScreen() {
    ModifierConfigCompose("Box") { modifier ->
        Box(modifier) {
            Text(text = "Box", style = MaterialTheme.typography.bodyLarge)
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