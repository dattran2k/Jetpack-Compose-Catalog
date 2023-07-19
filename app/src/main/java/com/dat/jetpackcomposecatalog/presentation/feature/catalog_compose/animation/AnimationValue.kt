@file:OptIn(ExperimentalAnimationGraphicsApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.core.designsystem.icon.MyIcons
import com.dat.jetpackcomposecatalog.data.model.catalog.CoordinateItem
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.MyBox
import com.dat.jetpackcomposecatalog.presentation.widget.TextHeadBloc
import com.dat.jetpackcomposecatalog.presentation.widget.TextTitle2Bloc
import com.dat.jetpackcomposecatalog.presentation.widget.TextTitleBloc
import com.dat.jetpackcomposecatalog.presentation.widget.animateAlignmentAsState
import kotlinx.coroutines.delay


@Composable
fun AnimationValueScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        ManualTransition()
        InfiniteTransition()
    }
}

@Composable
fun ManualTransition() {
    var state by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        TextHeadBloc("ManualTransition")
        TextTitleBloc("AnimateFloat")
        Box(modifier = Modifier.fillMaxWidth(1f), contentAlignment = Alignment.Center) {
            ClickCard(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable {
                        state = !state
                    }, title = "Click me to animate below"
            )
        }
        ManualTransitionFloat(state)
        CombineTransition(state) {
            state = !state
        }
        ManualTransitionAlignment()
        ManualTransitionColor()
    }
}

@Composable
fun InfiniteTransition() {
    Column {
        TextHeadBloc("InfiniteTransition")
        TextTitleBloc("AnimateFloat")
        InfiniteTransitionFloat()
        TextTitleBloc("AnimateColor")
        InfiniteTransitionColor(MaterialTheme.colorScheme.primary, Color.Black)
    }
}

@Composable
fun ManualTransitionAlignment() {
    var state by remember {
        mutableStateOf(0)
    }
    val alignment by animateAlignmentAsState(
        when (state % 9) {
            0 -> Alignment.TopStart
            1 -> Alignment.TopCenter
            2 -> Alignment.TopEnd
            3 -> Alignment.CenterStart
            4 -> Alignment.Center
            5 -> Alignment.CenterEnd
            6 -> Alignment.BottomStart
            7 -> Alignment.BottomCenter
            else -> Alignment.BottomEnd
        }
    )
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextTitleBloc(title = "animateAlignment")
        ClickCard(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    state++
                }, title = "Click me to animate alignment"
        )
        Box(
            Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .padding(4.dp)
                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.large)
        ) {
            MyBox(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(1f)
                    .align(alignment),
                color = Color.Red
            )
        }
    }
}

@Composable
fun ManualTransitionFloat(state: Boolean) {
    val rotation by animateFloatAsState(
        targetValue = if (state) 290f else 0f, animationSpec = tween()
    )
    val scale by animateFloatAsState(
        targetValue = if (state) 2f else 1f, animationSpec = tween()
    )
    val alpha by animateFloatAsState(
        targetValue = if (state) 0f else 1f, animationSpec = tween()
    )
    val translation by animateFloatAsState(
        targetValue = if (state) -50f else 0f, animationSpec = tween()
    )
    Column {
        AnimateRotation(rotation)
        AnimateScale(scale)
        AnimateAlpha(alpha)
        AnimateTranslation(translation)
    }
}

val listItem = listOf(
    CoordinateItem(-1.2f, -1.2f),
    CoordinateItem(0.0f, -1.2f),
    CoordinateItem(1.2f, -1.2f),
    CoordinateItem(-1.2f, 0.0f),
    CoordinateItem(1.2f, 0.0f),
    CoordinateItem(-1.2f, 1.2f),
    CoordinateItem(0.0f, 1.2f),
    CoordinateItem(1.2f, 1.2f),
    CoordinateItem(0.0f, 0.0f, scale = 1.2f),
)

@Composable
fun CombineTransition(state: Boolean, updateState: () -> Unit) {
    Column {
        TextTitle2Bloc("translationX,Y scaleX,Y rotationZ")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CoordinateItem.size * 4),
            contentAlignment = Alignment.Center
        ) {
            listItem.forEachIndexed { index, coordinate ->
                UltimateCombineItem(state, coordinate, index, updateState)
            }
        }
    }


}

@Composable
fun UltimateCombineItem(
    state: Boolean,
    coordinateItem: CoordinateItem,
    index: Int,
    updateState: () -> Unit
) {
    val density = LocalDensity.current
    val x = with(density) { CoordinateItem.size.toPx() } * coordinateItem.transitionX
    val y = with(density) { CoordinateItem.size.toPx() } * coordinateItem.transitionY
    Image(
        modifier = Modifier
            .size(CoordinateItem.size)
            .graphicsLayer(
                rotationZ = animateFloatAsState(
                    targetValue = if (state) coordinateItem.rotateZ else 0f,
                    tween(500)
                ).value,
                translationY = animateFloatAsState(targetValue = if (state) y else index * 5f).value,
                translationX = animateFloatAsState(targetValue = if (state) x else index * 2f).value,
                scaleX = animateFloatAsState(targetValue = if (state) coordinateItem.scale else 2f).value,
                scaleY = animateFloatAsState(targetValue = if (state) coordinateItem.scale else 2f).value,
            )
            .shadow(
                elevation = animateDpAsState(targetValue = if (state) 20.dp else Dp.Unspecified).value,
                shape = CircleShape,
                ambientColor = Color.Red,
                spotColor = Color.Red,
                clip = true
            )
            .clickable(onClick = updateState),
        painter = painterResource(MyIcons.ImDefault),
        contentDescription = "MyAvatar"
    )
}

@Composable
fun ManualTransitionColor() {
    var state by remember {
        mutableStateOf(true)
    }
    val colorContainer by animateColorAsState(
        targetValue = if (state)
            MaterialTheme.colorScheme.primary
        else
            Color.Green,
        animationSpec = tween(500)
    )
    val colorText by animateColorAsState(
        targetValue = if (state)
            MaterialTheme.colorScheme.onPrimary
        else
            Color.DarkGray,
        animationSpec = tween(500)
    )
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextTitleBloc(title = "AnimateColor")
        ClickCard(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    state = !state
                },
            title = "Click me to animate color",
            containerColor = colorContainer,
            textColor = colorText
        )
    }
}

@Composable
fun InfiniteTransitionFloat() {
    val infiniteTransitionTransition = rememberInfiniteTransition()
    val translation by infiniteTransitionTransition.animateFloat(
        initialValue = -50f, targetValue = 100f, animationSpec = getInfiniteRepeatableSpec()
    )
    val infiniteTransitionAlpha = rememberInfiniteTransition()
    val alpha by infiniteTransitionAlpha.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = getInfiniteRepeatableSpec()
    )

    val infiniteTransitionScale = rememberInfiniteTransition()
    val scale by infiniteTransitionScale.animateFloat(
        initialValue = 1f, targetValue = 3f, animationSpec = getInfiniteRepeatableSpec()
    )
    val infiniteTransitionRotation = rememberInfiniteTransition()
    val rotation by infiniteTransitionRotation.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = getInfiniteRepeatableSpec()
    )
    var state by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = state) {
        delay(2000)
        state = !state
    }
    Column {
        AnimateRotation(rotation)
        AnimateScale(scale)
        AnimateAlpha(alpha)
        AnimateTranslation(translation)
        CombineTransition(state) {
            state = !state
        }
    }
}

// ============================================================================================
@Composable
fun AnimateRotation(rotation: Float) {
    ContentContainer(title = "rotationX,Y,Z = ${rotation.toInt()} ") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MyBox(
                modifier = Modifier.graphicsLayer {
                    rotationZ = rotation
                }, color = Color.Red
            )
            MyBox(
                modifier = Modifier.graphicsLayer {
                    rotationX = rotation
                }, color = Color.Blue
            )
            MyBox(
                modifier = Modifier.graphicsLayer {
                    rotationY = rotation
                }, color = Color.Yellow
            )
        }
    }
}

@Composable
fun AnimateScale(scale: Float) {
    ContentContainer(title = "scaleXY  = ${scale.toInt()}") {
        MyBox(modifier = Modifier.scale(scaleX = scale, scaleY = scale), color = Color.Red)
    }
}

@Composable
fun AnimateAlpha(value: Float) {
    ContentContainer(title = "Alpha  = ${value.toInt()}") {
        MyBox(
            modifier = Modifier.alpha(value), color = Color.Blue
        )
    }
}

@Composable
fun AnimateTranslation(translation: Float) {
    ContentContainer(title = "translationXY = ${translation.toInt()}") {
        MyBox(
            modifier = Modifier.graphicsLayer(
                translationY = translation, translationX = translation
            ), color = Color.Red
        )
    }
}
// =============================================================================================


@Composable
fun InfiniteTransitionColor(initialColor: Color, targetColor: Color) {
    val infiniteTransition = rememberInfiniteTransition()
    val value by infiniteTransition.animateColor(
        initialValue = initialColor,
        targetValue = targetColor,
        animationSpec = getInfiniteRepeatableSpec()
    )
    ContentContainer(title = "color  = ${value.value}") {
        MyBox(color = value, useItemImage = false)
    }
}
// =============================================================================================


@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextTitle2Bloc(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun ClickCard(
    modifier: Modifier = Modifier,
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = textColor
            ),
        )
    }
}

@Composable
private fun <T> getInfiniteRepeatableSpec(): InfiniteRepeatableSpec<T> = infiniteRepeatable(
    animation = getTween(), repeatMode = RepeatMode.Reverse
)

fun <T> getTween() = tween<T>(
    durationMillis = 3000, easing = LinearEasing
)

@Preview
@Composable
fun AnimationValuePreview() {
    JetpackComposeCatalogTheme {
        AnimationValueScreen(
            modifier = Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        )
    }
}