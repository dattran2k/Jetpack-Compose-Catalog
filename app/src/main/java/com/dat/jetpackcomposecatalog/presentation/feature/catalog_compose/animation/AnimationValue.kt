@file:OptIn(ExperimentalAnimationGraphicsApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme


@Composable
fun AnimationValueScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "AnimateFloat", style = MaterialTheme.typography.bodyLarge)
        AnimateFloat()
        Text(text = "AnimateValue", style = MaterialTheme.typography.bodyLarge)
        AnimateValue()
        Text(text = "AnimateColor", style = MaterialTheme.typography.bodyLarge)
        AnimateColor()
    }
}

// =============================================================================================
// =============================================================================================
// =============================================================================================

@Composable
fun AnimateFloat() {
    val infiniteTransitionTransition = rememberInfiniteTransition()
    val translation by infiniteTransitionTransition.animateFloat(
        initialValue = -50f,
        targetValue = 100f,
        animationSpec = getInfiniteRepeatableSpec()
    )

    val infiniteTransitionAlpha = rememberInfiniteTransition()
    val alpha by infiniteTransitionAlpha.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = getInfiniteRepeatableSpec()
    )

    val infiniteTransitionScale = rememberInfiniteTransition()
    val scale by infiniteTransitionScale.animateFloat(
        initialValue = 1f,
        targetValue = 3f,
        animationSpec = getInfiniteRepeatableSpec()
    )
    val infiniteTransitionRotation = rememberInfiniteTransition()
    val rotation by infiniteTransitionRotation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = getInfiniteRepeatableSpec()
    )
    val infiniteTransitionRotation2 = rememberInfiniteTransition()
    val rotation2 by infiniteTransitionRotation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = getInfiniteRepeatableSpec()
    )
    Column {
        AnimateRotation(rotation)
        AnimateRotation(rotation2)
        AnimateScale(scale)
        AnimateAlpha(alpha)
        AnimateTranslation(translation)
    }
}

@Composable
fun AnimateRotation(rotation: Float) {
    Column {
        ContentContainer(title = "rotationZ = ${rotation.toInt()} ") {
            Box(modifier = Modifier
                .size(40.dp)
                .graphicsLayer {
                    rotationZ = rotation
                }
                .background(Color.Red))
        }
        ContentContainer(title = "rotationX = ${rotation.toInt()} ") {
            Box(modifier = Modifier
                .size(40.dp)
                .graphicsLayer {
                    rotationX = rotation
                }
                .background(Color.Blue))
        }
        ContentContainer(title = "rotationY = ${rotation.toInt()} ") {
            Box(modifier = Modifier
                .size(40.dp)
                .graphicsLayer {
                    rotationY = rotation
                }
                .background(Color.Yellow))
        }
    }

}

@Composable
fun AnimateScale(scale: Float) {
    ContentContainer(title = "scaleX  = ${scale.toInt()}") {
        Box(modifier = Modifier
            .size(40.dp)
            .graphicsLayer {
                scaleX = scale
            }
            .background(Color.Red))
    }
}

@Composable
fun AnimateAlpha(value: Float) {
    ContentContainer(title = "Alpha  = ${value.toInt()}") {
        Box(
            modifier = Modifier
                .size(40.dp)
                .graphicsLayer(alpha = value)
                .background(Color.Blue)
        )
    }
}

@Composable
fun AnimateTranslation(translation: Float) {
    Column {
        ContentContainer(title = "translationX  = ${translation.toInt()}") {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .graphicsLayer(translationX = translation)
                    .background(Color.Yellow)
            )
        }
        ContentContainer(title = "translationY  = ${translation.toInt()}") {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .graphicsLayer(translationY = translation)
                    .background(Color.Red)
            )
        }
        ContentContainer(title = "translationX translationY = ${translation.toInt()}") {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .graphicsLayer(
                        translationY = translation,
                        translationX = translation
                    )
                    .background(Color.Blue)
            )
        }
    }

}
// =============================================================================================
// =============================================================================================
// =============================================================================================

@Composable
fun AnimateValue() {
    AnimateBoolean()
}

@Composable
fun AnimateBoolean() {
    val infiniteTransition = rememberInfiniteTransition()
    val value by infiniteTransition.animateValue(
        initialValue = true,
        targetValue = false,
        typeConverter = TwoWayConverter(
            { AnimationVector1D(if (it) 1f else -1f) },
            { it.value >= 0 }
        ),
        animationSpec = getInfiniteRepeatableSpec()
    )
    ContentContainer(modifier = Modifier.height(60.dp), title = "Boolean visible  = $value") {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            AnimatedVisibility(
                visible = value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            }
        }
    }
}
// =============================================================================================
// =============================================================================================
// =============================================================================================

@Composable
fun AnimateColor() {
    val infiniteTransition = rememberInfiniteTransition()
    val value by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.White,
        animationSpec = getInfiniteRepeatableSpec()
    )
    ContentContainer(title = "color  = $value") {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(value)
        )
    }
}
// =============================================================================================
// =============================================================================================
// =============================================================================================

@Composable
private fun <T> getInfiniteRepeatableSpec(): InfiniteRepeatableSpec<T> =
    infiniteRepeatable<T>(
        animation = tween(
            durationMillis = 3000,
            easing = LinearEasing
        ),
        repeatMode = RepeatMode.Reverse
    )

private fun <T> getInfiniteRepeatableSpec2(): InfiniteRepeatableSpec<T> =
    infiniteRepeatable<T>(
        animation = tween(
            durationMillis = 3000,
            easing = LinearEasing
        ),
        repeatMode = RepeatMode.Reverse
    )

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(8.dp)) {
        content()
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
@Composable
fun AnimationValuePreview() {
    JetpackComposeCatalogTheme {
        AnimationValueScreen(modifier = Modifier.statusBarsPadding())
    }
}