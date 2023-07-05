package com.dat.jetpackcomposecatalog.presenstation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmptyBox(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit = {}) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(60.dp)
            .background(Color.Red),
        contentAlignment = Alignment.Center

    ) {
        content.invoke(this)
    }
}