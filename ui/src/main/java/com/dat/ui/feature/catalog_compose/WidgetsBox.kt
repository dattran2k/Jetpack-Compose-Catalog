@file:OptIn(ExperimentalMaterial3Api::class)

package com.dat.ui.feature.catalog_compose

//import com.dat.designsystem.component.ModifierConfigCompose
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dat.designsystem.theme.JetpackComposeCatalogTheme

@Composable
fun WidgetsBox() {
//    ModifierConfigCompose("Box") { modifier ->
//        Box(modifier.background(Color.Red)) {
//            Text(text = "Hello", style = MaterialTheme.typography.bodyLarge)
//        }
//    }
}

@Preview
@Composable
fun PreviewBox() {
    JetpackComposeCatalogTheme {
        WidgetsBox()
    }
}