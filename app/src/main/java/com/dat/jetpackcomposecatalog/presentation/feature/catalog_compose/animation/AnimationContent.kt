@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import com.dat.jetpackcomposecatalog.data.model.NumberShow
import kotlinx.coroutines.delay

object AnimationContent : BaseAnimationScreen() {
    @Composable
    override fun Screen(modifier: Modifier) {
        Column(modifier = modifier) {
            Row {
                CountIncrease()
            }
        }
    }

    @Composable
    fun CountIncrease(modifier: Modifier = Modifier) {
        var count by remember { mutableStateOf(NumberShow(0, 9, 5)) }
        LaunchedEffect(count) {
            delay(200)
            count = count.plus()
        }
        Row(modifier) {
            // https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent
            AnimatedContentNumberCount(count.first)
            AnimatedContentNumberCount(count.second)
            AnimatedContentNumberCount(count.third)
        }
    }

    @Composable
    private fun AnimatedContentNumberCount(count: Int) {
        Card(modifier = Modifier.padding(2.dp)) {
            AnimatedContent(targetState = count,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState || targetState == 0) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        ContentTransform(
                            slideInVertically { height -> 2 * height } + fadeIn(),
                            slideOutVertically { height -> -height } + fadeOut()
                        )
                    } else if (targetState < initialState) {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        ContentTransform(
                            slideInVertically { height -> -height } + fadeIn(),
                            slideOutVertically { height -> height } + fadeOut()
                        )

                    } else {
                        ContentTransform(
                            EnterTransition.None,
                            ExitTransition.None
                        )
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "$targetCount",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewAnimationContent() {
    AnimationContent.Preview()
}