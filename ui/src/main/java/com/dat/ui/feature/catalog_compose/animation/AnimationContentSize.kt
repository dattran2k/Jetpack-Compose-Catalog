package com.dat.ui.feature.catalog_compose.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dat.core.model.ui.MyAnim
import com.dat.core.model.ui.MyAnim.easing
import com.dat.core.model.ui.MyAnim.spring
import com.dat.designsystem.component.TextHeadBloc
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.designsystem.theme.getColorByIndex
import kotlinx.coroutines.delay

@Composable
fun AnimationContentSizeRoute() {
    AnimationContentSizeScreen(Modifier.verticalScroll(rememberScrollState()))
}

@Composable
fun AnimationContentSizeScreen(modifier: Modifier = Modifier) {
    var isExpand by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isExpand) {
        delay(2_000)
        isExpand = !isExpand
    }
    Column(modifier) {
        TextHeadBloc("animateContentSize")
        MyAnim.TypeAnim.values().forEach { typeAnim ->
            when (typeAnim) {
                MyAnim.TypeAnim.Easing -> easing
                MyAnim.TypeAnim.Spring -> spring
            }.toList().forEachIndexed { index, pair ->
                ContentSize(
                    Modifier,
                    isVisible = isExpand,
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
            .background(color, shape = MaterialTheme.shapes.large)
            .animateContentSize(animationSpec.second),
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(if (isVisible) 1f else 0.1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = animationSpec.first,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black
                ),
                maxLines = 2,
                minLines = 2,
            )
        }
    }
}


@Preview
@Composable
fun AnimationContentSizePreview() {
    JetpackComposeCatalogTheme {
        AnimationContentSizeScreen(modifier = Modifier.statusBarsPadding())
    }
}