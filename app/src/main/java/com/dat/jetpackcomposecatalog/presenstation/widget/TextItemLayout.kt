package com.dat.jetpackcomposecatalog.presenstation.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextItemLayout(text: String, xStart: Float = -50f, yStart: Float = -50f) {
    val offsetX = remember { Animatable(xStart) }
    val offsetY = remember { Animatable(yStart) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .offset(offsetX.value.dp, offsetX.value.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(0.5.dp, color = Color.LightGray)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }

    LaunchedEffect(text) {
        offsetX.animateTo(
            targetValue = 0f, animationSpec = tween(
                durationMillis = 200, delayMillis = 0
            )
        )
        offsetY.animateTo(
            targetValue = 0f, animationSpec = tween(
                durationMillis = 200, delayMillis = 0
            )
        )
    }
}