@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import kotlinx.coroutines.delay

@Composable
fun AnimationContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row {
            CountIncrease()
            Spacer(modifier = Modifier.width(16.dp))
            CountDecrease()
        }
    }

}

@Composable
fun CountIncrease(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(count) {
        if (count >= Int.MAX_VALUE - 100)
            return@LaunchedEffect
        delay(500)
        count++
    }
    Row(modifier) {
        // https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent
        AnimatedContentNumberCount(count)
    }
}
@Composable
fun CountDecrease(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(count) {
        if (count <= Int.MIN_VALUE + 100)
            return@LaunchedEffect
        delay(500)
        count--
    }
    Row(modifier) {
        // https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent
        AnimatedContentNumberCount(count)
    }
}

@Composable
private fun AnimatedContentNumberCount(count: Int) {
    AnimatedContent(targetState = count,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                ContentTransform(
                    slideInVertically { height -> 2 * height } + fadeIn(),
                    slideOutVertically { height -> -height } + fadeOut()
                )
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                ContentTransform(
                    slideInVertically { height -> -height } + fadeIn(),
                    slideOutVertically { height -> height } + fadeOut()
                )
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }
    ) { targetCount ->
        Text(text = "Count: $targetCount")
    }
}

@Preview
@Composable
fun PreviewAnimationContent() {
    JetpackComposeCatalogTheme {
        AnimationContent(modifier = Modifier.statusBarsPadding())
    }
}