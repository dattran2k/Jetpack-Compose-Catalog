package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.core.common.DELAY
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation.MyAnim.easing
import com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation.MyAnim.spring
import com.dat.jetpackcomposecatalog.presentation.theme.getColorByIndex
import kotlinx.coroutines.delay

object AnimationContentSize : BaseAnimationScreen() {
    @Composable
    override fun Screen(modifier: Modifier) {
        var isExpand by remember {
            mutableStateOf(true)
        }
        LaunchedEffect(isExpand) {
            delay(DELAY)
            isExpand = !isExpand
        }
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            AnimationGroup(isExpand)
        }
    }

    @Composable
    fun AnimationGroup(
        isVisible: Boolean,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                MyAnim.TypeAnim.values().forEach { typeAnim ->
                    when (typeAnim) {
                        MyAnim.TypeAnim.Easing -> easing
                        MyAnim.TypeAnim.Spring -> spring
                    }.toList().forEachIndexed { index, pair ->
                        ContentSize(
                            Modifier,
                            isVisible = isVisible,
                            animationSpec = pair.first to MyAnim.getFiniteAnimationSpec(
                                typeAnim,
                                pair.first
                            ),
                            color = getColorByIndex(index)
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun ContentSize(
        modifier: Modifier = Modifier,
        isVisible: Boolean,
        animationSpec: Pair<String, FiniteAnimationSpec<IntSize>>,
        color: Color,
    ) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .background(color)
                .animateContentSize(animationSpec.second),
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(if (isVisible) 1f else 0.1f)
            ) {
                Text(
                    text = animationSpec.first,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    minLines = 2
                )
            }
        }
    }
}


@Preview
@Composable
fun AnimationContentSizePreview() {
    AnimationContentSize.Preview()
}