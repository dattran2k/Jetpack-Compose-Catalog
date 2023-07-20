@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.feature.catalog_compose.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dat.jetpackcomposecatalog.theme.JetpackComposeCatalogTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun AnimationTest(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }
    LaunchedEffect(visible) {
        delay(2_000)
        visible = !visible
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Fade in/out the background and the foreground.
        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.DarkGray)
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(
                        // Slide in/out the inner box.
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                    .fillMaxSize(0.5f)
                    .background(Color.Red)
            ) {
                // Content of the notification…
            }
        }
    }
}

@Composable
fun BouncyRope(modifier: Modifier = Modifier) {
    var startCoOrdinate by remember {
        mutableStateOf(Offset(0f, 200f))
    }
    var endCoOrdinate by remember {
        mutableStateOf(Offset(300f, 200f))
    }
    val midPoint by remember {
        derivedStateOf {
            val distance = (endCoOrdinate.x - startCoOrdinate.x)
            Offset(
                (endCoOrdinate.x - startCoOrdinate.x) / 2f,
                endCoOrdinate.y + distance
            )
        }
    }
    val path = remember {
        Path()
    }
    val midPointAnimated = animateOffsetAsState(
        midPoint,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )
    val color = MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Bouncy Rope",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Canvas(
            modifier = Modifier
                .offset {
                    IntOffset(
                        startCoOrdinate.x.roundToInt(),
                        startCoOrdinate.y.roundToInt()
                    )
                }
                .pointerInput("drag") {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        startCoOrdinate += dragAmount
                    }
                }) {
            val radius = 15.dp.toPx()
            drawCircle(color, radius = radius)
        }

        Canvas(
            modifier = Modifier
                .offset {
                    IntOffset(
                        endCoOrdinate.x.roundToInt(),
                        endCoOrdinate.y.roundToInt()
                    )
                }
                .pointerInput("drag") {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        endCoOrdinate += dragAmount
                    }
                }) {
            val radius = 15.dp.toPx()
            drawCircle(color, radius = radius)
        }

        Canvas(modifier = Modifier) {
            path.reset()
            path.moveTo(startCoOrdinate.x, startCoOrdinate.y)
            path.quadraticBezierTo(
                midPointAnimated.value.x,
                midPointAnimated.value.y,
                endCoOrdinate.x,
                endCoOrdinate.y
            )

            drawPath(path, color, style = Stroke(8.dp.toPx()))
        }
    }
}

@Preview
@Composable
fun Preview() {
    JetpackComposeCatalogTheme {
//        AnimationTest(Modifier.statusBarsPadding())
        BouncyRope(Modifier.statusBarsPadding())
    }
}