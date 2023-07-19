@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.dat.jetpackcomposecatalog.core.common.DELAY
import com.dat.jetpackcomposecatalog.data.model.catalog.MyAnim
import com.dat.jetpackcomposecatalog.data.model.catalog.MyAnim.easing
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.MyBox
import com.dat.jetpackcomposecatalog.presentation.widget.TextHeadBloc
import com.dat.jetpackcomposecatalog.presentation.widget.TextTitleBloc
import kotlinx.coroutines.delay

@Composable
fun AnimationVisibilityScreen(modifier: Modifier = Modifier) {
    var isVisible by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isVisible) {
        delay(DELAY)
        isVisible = !isVisible
    }
    Column(modifier) {
        TextHeadBloc("AnimatedVisibility")
        MyAnim.Transition.values().forEach {
            AnimationGroup(it, isVisible)
        }
    }
}

@Composable
fun AnimationGroup(
    anim: MyAnim.Transition,
    isVisible: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextTitleBloc(title = anim.name, Modifier.padding(4.dp))
        val getAnimGroup = MyAnim.getAnimEnterExitGroup(
            anim,
            MyAnim.TypeAnim.Easing,
            easing.toList().first().first
        )
        ContentVisibility(
            modifier = Modifier
                .height(100.dp)
                .aspectRatio(1f),
            isVisible = isVisible,
            enter = getAnimGroup.enter.second,
            exit = getAnimGroup.exit.second,
        )
    }
}

@Composable
private fun ContentVisibility(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    enter: EnterTransition,
    exit: ExitTransition
) {
    Box(
        modifier = modifier.padding(4.dp),
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = enter,
            exit = exit
        ) {
            MyBox(color = Color.Transparent)
        }
    }
}


@Preview
@Composable
fun AnimationVisibilityPreview() {
    JetpackComposeCatalogTheme {
        AnimationVisibilityScreen(
            Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        )
    }
}