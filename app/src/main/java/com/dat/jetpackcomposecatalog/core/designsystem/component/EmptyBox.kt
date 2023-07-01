package com.dat.jetpackcomposecatalog.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmptyBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(60.dp)
            .background(Color.Red)
    )
}