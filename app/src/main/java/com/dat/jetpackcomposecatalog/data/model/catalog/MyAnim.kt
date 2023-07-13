@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
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
import androidx.compose.ui.Alignment
import com.dat.jetpackcomposecatalog.core.common.DURATION


object MyAnim {
    class MyAnimEnterExitGroup(
        val enter: Pair<String, EnterTransition>,
        val exit: Pair<String, ExitTransition>,
    )

    enum class MyAnimTransition {
        SlideVertically,
        SlideHorizontally,
        Fade,
        Scale,
        ExpandShrink,
        ExpandShrinkHorizontally,
        ExpandShrinkVertically
    }

    enum class TypeAnim {
        Easing,
        Spring
    }

    val mapSpring = hashMapOf(
        "DampingRatioHighBouncy" to Spring.DampingRatioHighBouncy,
        "DampingRatioMediumBouncy" to Spring.DampingRatioMediumBouncy,
        "DampingRatioLowBouncy" to Spring.DampingRatioLowBouncy,
        "DampingRatioNoBouncy" to Spring.DampingRatioNoBouncy,
    )

    val groupEasing = hashMapOf(
        "FastOutSlowInEasing" to FastOutSlowInEasing,
        "LinearOutSlowInEasing" to LinearOutSlowInEasing,
        "FastOutLinearInEasing" to FastOutLinearInEasing,
        "LinearEasing" to LinearEasing,
    )
    fun <T> getSpring(key: String) = spring<T>(dampingRatio = mapSpring[key]!!, stiffness = Spring.StiffnessMedium)
    fun <T> getTween(key: String) = tween<T>(DURATION, easing = groupEasing[key]!!)
    fun <T> getAnim(type : TypeAnim,key : String): FiniteAnimationSpec<T> {
        return when (type) {
            TypeAnim.Easing -> getTween(key)
            TypeAnim.Spring -> getSpring(key)
        }
    }
    fun getAnimEnterExitGroup(anim: MyAnimTransition, type: TypeAnim, key: String): MyAnimEnterExitGroup {
        return when (anim) {
            MyAnimTransition.SlideVertically -> MyAnimEnterExitGroup(
                "slideInVertically" to slideInVertically(
                    animationSpec = getAnim(type,key),
                    initialOffsetY = { -it }),
                "slideOutVertically" to slideOutVertically(
                    animationSpec = getAnim(type,key),
                    targetOffsetY = { -it })
            )

            MyAnimTransition.SlideHorizontally -> MyAnimEnterExitGroup(
                "slideInHorizontally" to slideInHorizontally(
                    animationSpec = getAnim(type,key),
                    initialOffsetX = { -it }),
                "slideOutHorizontally" to slideOutHorizontally(
                    animationSpec = getAnim(type,key),
                    targetOffsetX = { -it })
            )

            MyAnimTransition.Fade -> MyAnimEnterExitGroup(
                "fadeIn" to fadeIn(animationSpec = getAnim(type,key), initialAlpha = 0f),
                "fadeOut" to fadeOut(animationSpec = getAnim(type,key), targetAlpha = 0f)
            )

            MyAnimTransition.Scale -> MyAnimEnterExitGroup(
                "scaleIn" to scaleIn(animationSpec = getAnim(type,key), initialScale = 0f),
                "scaleOut" to scaleOut(animationSpec = getAnim(type,key), targetScale = 0f)
            )

            MyAnimTransition.ExpandShrink -> MyAnimEnterExitGroup(
                "expandIn" to expandIn(animationSpec = getAnim(type,key), Alignment.TopEnd),
                "shrinkOut" to shrinkOut(animationSpec = getAnim(type,key), Alignment.TopEnd)
            )

            MyAnimTransition.ExpandShrinkHorizontally -> MyAnimEnterExitGroup(
                "expandHorizontally" to expandHorizontally(animationSpec = getAnim(type,key), Alignment.Start),
                "shrinkHorizontally" to shrinkHorizontally(animationSpec = getAnim(type,key), Alignment.End)
            )

            MyAnimTransition.ExpandShrinkVertically -> MyAnimEnterExitGroup(
                "expandVertically" to expandVertically(animationSpec = getAnim(type,key), Alignment.Top),
                "shrinkVertically" to shrinkVertically(animationSpec = getAnim(type,key), Alignment.Bottom)
            )
        }
    }

}