@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.ui.feature.catalog_compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dat.designsystem.component.TextHeadBloc
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.ui.common.ui_model.Digit
import kotlinx.coroutines.delay

const val DELAY = 300

@Composable
fun AnimatedContentScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TextHeadBloc("AnimatedContent")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountIncrease()
            CountDecrease()
        }
    }
}

@Composable
fun CountIncrease() {
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(count) {
        delay(DELAY.toLong())
        count++
    }
    AnimatedContentNumberCount(count)
}

@Composable
fun CountDecrease() {
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(count) {
        delay(DELAY.toLong())
        count--
    }
    AnimatedContentNumberCount(count)

}

// https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent
@Composable
private fun AnimatedContentNumberCount(count: Int) {
    Row {
        count.toString().reversed()
            .mapIndexed { index, char -> Digit(char, count, index) }
            .reversed()
            .forEach { digit ->
                AnimatedContent(
                    targetState = digit,
                    transitionSpec = {
                        // Compare the incoming number with the previous number.
                        (if (targetState > initialState) {
                            // If the target number is larger, it slides up and fades in
                            // while the initial (smaller) number slides up and fades out.
                            ContentTransform(
                                slideInVertically(tween(DELAY)) { height -> height } + fadeIn(),
                                slideOutVertically(tween(DELAY)) { height -> -height } + fadeOut()
                            )
                        } else if (targetState < initialState) {
                            // If the target number is smaller, it slides down and fades in
                            // while the initial number slides down and fades out.
                            ContentTransform(
                                slideInVertically(tween(DELAY)) { height -> -height } + fadeIn(),
                                slideOutVertically(tween(DELAY)) { height -> height } + fadeOut()
                            )
                        } else {
                            ContentTransform(
                                EnterTransition.None,
                                ExitTransition.None
                            )
                        }).using(
                            // Disable clipping since the faded slide-in/out should
                            // be displayed out of bounds.
                            SizeTransform(clip = false)
                        )
                    }
                ) { targetCount ->
                    Text(
                        text = "${targetCount.singleDigit}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 40.sp
                        )
                    )
                }
            }
    }
}

@Preview
@Composable
fun PreviewAnimationContent() {
    JetpackComposeCatalogTheme {
        AnimatedContentScreen(modifier = Modifier.statusBarsPadding())
    }
}