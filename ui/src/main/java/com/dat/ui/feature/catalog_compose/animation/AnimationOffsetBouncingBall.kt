@file:OptIn(ExperimentalTextApi::class)

package com.dat.ui.feature.catalog_compose.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toSize
import com.dat.core.designsystem.component.MySwitchButtonCompose
import com.dat.core.designsystem.component.ValueSlider
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun AnimationOffsetBouncingBallRoute(modifier: Modifier = Modifier) {
    AnimationOffsetBouncingBallScreen(modifier)
}

@Composable
fun AnimationOffsetBouncingBallScreen(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    var ballSizePx by remember {
        mutableStateOf(400f)
    }
    var targetBallSizePx by remember {
        mutableStateOf(400f)
    }
    LaunchedEffect(key1 = targetBallSizePx) {
        animate(
            initialValue = ballSizePx, targetValue = targetBallSizePx, animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium
            )
        ) { value, _ ->
            ballSizePx = value
        }
    }
    var isAutoBouncing by remember {
        mutableStateOf(false)
    }
    var parentSize by remember {
        mutableStateOf(Size.Zero)
    }
    var isStart by remember {
        mutableStateOf(true)
    }
    var coOrdinate by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    val coOrdinateAnimated = animateOffsetAsState(
        coOrdinate, animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessVeryLow
        ),
        label = "coOrdinateAnimated"
    )
    if (isAutoBouncing) LaunchedEffect(key1 = isStart) {
        delay(2000)
        val y = if (isStart) 0f else parentSize.height - ballSizePx
        coOrdinate = Offset(parentSize.width / 2 - ballSizePx / 2, y)
        isStart = !isStart
    }
    Column(modifier = modifier) {
        Box(modifier = Modifier
            .weight(1f)
            .onGloballyPositioned {
                parentSize = it.parentLayoutCoordinates?.size?.toSize() ?: Size.Zero
            }) {
            Box(modifier = Modifier
                .offset {
                    IntOffset(
                        coOrdinateAnimated.value.x.roundToInt(),
                        coOrdinateAnimated.value.y.roundToInt()
                    )
                }
                .size(with(density) { ballSizePx.toDp() })
                .background(Color.Green, shape = CircleShape)
                .pointerInput("drag") {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        coOrdinate += dragAmount
                    }
                }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = coOrdinate.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Black, color = Color.Black
                    )
                )
            }

        }
        MySwitchButtonCompose(title = "Auto jump :",
            isSelected = isAutoBouncing,
            onSelectedCallback = {
                isAutoBouncing = it
            })
        // config
        ValueSlider(title = "Ball size",
            value = targetBallSizePx.toInt(),
            from = 100,
            to = 1000,
            onValueChange = {
                targetBallSizePx = it.toFloat()
            })
    }

}

@Preview
@Composable
fun PreviewAnimationBouncingBall() {
    JetpackComposeCatalogTheme {
        AnimationOffsetBouncingBallScreen(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}