
package com.dat.jetpackcomposecatalog.core.designsystem.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSelector(selectedColor: (color: Color) -> Unit = {}) {
    FlowRow {
        CustomChip(Color.Cyan, selectedColor)
        CustomChip(Color.Blue, selectedColor)
        CustomChip(Color.Black, selectedColor)
        CustomChip(Color.Red, selectedColor)
        CustomChip(Color.Green, selectedColor)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomChip(color: Color = Color.Blue, selectedColor: (color: Color) -> Unit = {}) {
    Chip(
        onClick = { selectedColor.invoke(color) },
        colors = ChipDefaults.chipColors(backgroundColor = color)
    ) {
    }
}

@Preview
@Composable
fun PreviewCustomChip() {
    JetpackComposeCatalogTheme {
        ColorSelector()
    }
}