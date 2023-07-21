package com.dat.designsystem.component

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun LotteAnimInfinityLoader(modifier: Modifier = Modifier, @RawRes resId: Int) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        reverseOnRepeat = true
    )
    Box(modifier = modifier) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = { progress },
        )
    }
}