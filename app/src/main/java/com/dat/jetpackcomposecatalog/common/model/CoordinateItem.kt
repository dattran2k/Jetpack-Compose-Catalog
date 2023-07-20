package com.dat.jetpackcomposecatalog.common.model

import androidx.compose.ui.unit.dp

class CoordinateItem(
    val transitionX: Float = 0f,
    val transitionY: Float = 0f,
    val rotateZ: Float = 360f,
    val scale: Float = 1f
) {
    companion object {
        val size = 100.dp
    }
}