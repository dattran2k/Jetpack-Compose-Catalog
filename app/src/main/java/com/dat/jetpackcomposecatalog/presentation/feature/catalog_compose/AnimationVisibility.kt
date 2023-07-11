@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.theme.getColorByIndex
import kotlinx.coroutines.delay


const val DELAY: Long = 5000
const val DURATION: Int = (DELAY * 4 / 5L).toInt()

enum class Anim {
    SlideVertically,
    SlideHorizontally,
    Fade,
    Scale,
    ExpandShrink,
    ExpandShrinkHorizontally,
    ExpandShrinkVertically
}

class AnimGroup(
    val enter: Pair<String, EnterTransition>,
    val exit: Pair<String, ExitTransition>,
)

val groupEasing = listOf(
    "FastOutSlowInEasing" to FastOutSlowInEasing,
    "LinearOutSlowInEasing" to LinearOutSlowInEasing,
    "FastOutLinearInEasing" to FastOutLinearInEasing,
    "LinearEasing" to LinearEasing,
)

fun getAnimEasing(anim: Anim, easing: Easing): AnimGroup {
    fun <T> getTween() = tween<T>(DURATION, easing = easing)
    return when (anim) {
        Anim.SlideVertically -> AnimGroup(
            "slideInVertically" to slideInVertically(
                animationSpec = getTween(),
                initialOffsetY = { -it }),
            "slideOutVertically" to slideOutVertically(
                animationSpec = getTween(),
                targetOffsetY = { -it })
        )

        Anim.SlideHorizontally -> AnimGroup(
            "slideInHorizontally" to slideInHorizontally(
                animationSpec = getTween(),
                initialOffsetX = { -it }),
            "slideOutHorizontally" to slideOutHorizontally(
                animationSpec = getTween(),
                targetOffsetX = { -it })
        )

        Anim.Fade -> AnimGroup(
            "slideInHorizontally" to fadeIn(animationSpec = getTween(), initialAlpha = 0f),
            "slideOutHorizontally" to fadeOut(animationSpec = getTween(), targetAlpha = 0f)
        )

        Anim.Scale -> AnimGroup(
            "scaleIn" to scaleIn(animationSpec = getTween(), initialScale = 0f),
            "scaleOut" to scaleOut(animationSpec = getTween(), targetScale = 0f)
        )

        Anim.ExpandShrink -> AnimGroup(
            "expandIn" to expandIn(animationSpec = getTween(), Alignment.TopEnd),
            "shrinkOut" to shrinkOut(animationSpec = getTween(), Alignment.TopEnd)
        )

        Anim.ExpandShrinkHorizontally -> AnimGroup(
            "expandHorizontally" to expandHorizontally(
                animationSpec = getTween(),
                Alignment.Start
            ),
            "shrinkHorizontally" to shrinkHorizontally(animationSpec = getTween(), Alignment.End)
        )

        Anim.ExpandShrinkVertically -> AnimGroup(
            "expandVertically" to expandVertically(animationSpec = getTween(), Alignment.Top),
            "shrinkVertically" to shrinkVertically(animationSpec = getTween(), Alignment.Bottom)
        )
    }
}

val mapSpring = hashMapOf(
    "DampingRatioHighBouncy" to Spring.DampingRatioHighBouncy,
    "DampingRatioMediumBouncy" to Spring.DampingRatioMediumBouncy,
    "DampingRatioLowBouncy" to Spring.DampingRatioLowBouncy,
    "DampingRatioNoBouncy" to Spring.DampingRatioNoBouncy,
)

fun getAnimSpring(anim: Anim, key: String): AnimGroup {
    fun <T> getSpring() = spring<T>(
        dampingRatio = mapSpring[key]!!,
        stiffness = Spring.StiffnessMedium
    )
    return when (anim) {
        Anim.SlideVertically -> AnimGroup(
            "slideInVertically" to slideInVertically(
                animationSpec = getSpring(),
                initialOffsetY = { -it }),
            "slideOutVertically" to slideOutVertically(
                animationSpec = getSpring(),
                targetOffsetY = { -it })
        )

        Anim.SlideHorizontally -> AnimGroup(
            "slideInHorizontally" to slideInHorizontally(
                animationSpec = getSpring(),
                initialOffsetX = { -it }),
            "slideOutHorizontally" to slideOutHorizontally(
                animationSpec = getSpring(),
                targetOffsetX = { -it })
        )

        Anim.Fade -> AnimGroup(
            "fadeIn" to fadeIn(animationSpec = getSpring(), initialAlpha = 0f),
            "fadeOut" to fadeOut(animationSpec = getSpring(), targetAlpha = 0f)
        )

        Anim.Scale -> AnimGroup(
            "scaleIn" to scaleIn(animationSpec = getSpring(), initialScale = 0f),
            "scaleOut" to scaleOut(animationSpec = getSpring(), targetScale = 0f)
        )

        Anim.ExpandShrink -> AnimGroup(
            "expandIn" to expandIn(animationSpec = getSpring(), Alignment.TopEnd),
            "shrinkOut" to shrinkOut(animationSpec = getSpring(), Alignment.TopEnd)
        )

        Anim.ExpandShrinkHorizontally -> AnimGroup(
            "expandHorizontally" to expandHorizontally(animationSpec = getSpring(), Alignment.Start),
            "shrinkHorizontally" to shrinkHorizontally(animationSpec = getSpring(), Alignment.End)
        )

        Anim.ExpandShrinkVertically -> AnimGroup(
            "expandVertically" to expandVertically(animationSpec = getSpring(), Alignment.Top),
            "shrinkVertically" to shrinkVertically(animationSpec = getSpring(), Alignment.Bottom)
        )
    }
}


@Composable
fun AnimationVisibility(
    modifier: Modifier = Modifier,
) {
    var isVisible by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isVisible) {
        delay(DELAY)
        isVisible = !isVisible
    }
    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        Anim.values().forEach {
            AnimationGroup(it, isVisible)
        }
    }
}

@Composable
fun AnimationGroup(
    anim: Anim,
    isVisible: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Text(text = anim.name, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(4.dp))
        Text(
            text = groupEasing.joinToString(separator = " - ") { it.first },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            groupEasing.forEachIndexed { index, pair ->
                val getAnimGroup = getAnimEasing(anim, pair.second)
                AnimationContainer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(1f),
                    isVisible,
                    getAnimGroup.enter.first to getAnimGroup.enter.second,
                    getAnimGroup.exit.first to getAnimGroup.exit.second,
                    getColorByIndex(index)
                )
            }
        }
        Text(
            text = mapSpring.toList().joinToString(separator = " - ") { it.first },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            mapSpring.toList().forEachIndexed { index, pair ->
                val getAnimGroup = getAnimSpring(anim, pair.first)
                AnimationContainer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(1f),
                    isVisible,
                    getAnimGroup.enter.first to getAnimGroup.enter.second,
                    getAnimGroup.exit.first to getAnimGroup.exit.second,
                    getColorByIndex(index)
                )
            }
        }
    }
}

@Composable
fun AnimationContainer(
    modifier: Modifier,
    isVisible: Boolean,
    enter: Pair<String, EnterTransition>,
    exit: Pair<String, ExitTransition>,
    color: Color,
) {
    Box(
        modifier = modifier.padding(4.dp),
//        border = BorderStroke(0.dp, color = Color.Transparent),
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = enter.second,
            exit = exit.second
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                border = BorderStroke(0.dp, color = Color.Transparent),
                colors = CardDefaults.cardColors(
                    containerColor = color
                )
            ) {
                Box(modifier = Modifier.background(color))
            }
        }
    }
}

@Preview
@Composable
fun AnimationVisibilityPreview() {
    JetpackComposeCatalogTheme(true) {
        AnimationVisibility(Modifier.statusBarsPadding())
    }
}