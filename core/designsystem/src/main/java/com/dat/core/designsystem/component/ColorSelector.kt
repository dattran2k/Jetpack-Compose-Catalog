package com.dat.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.getColorByIndex

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSelector(selectedColor: (color: Color) -> Unit = {}) {
    FlowRow {
        (0..6).forEach {
            val color = getColorByIndex(it)
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .background(color = color, shape = CircleShape)
                    .clickable {
                        selectedColor.invoke(color)
                    },
            )
        }
    }
}


@Preview
@Composable
fun PreviewCustomChip() {
    JetpackComposeCatalogTheme {
        ColorSelector()
    }
}