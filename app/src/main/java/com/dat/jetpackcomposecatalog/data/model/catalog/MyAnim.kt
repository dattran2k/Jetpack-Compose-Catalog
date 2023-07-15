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
    class EnterExitTransition(
        val enter: Pair<String, EnterTransition>,
        val exit: Pair<String, ExitTransition>,
    )

    enum class Transition {
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

    val spring = hashMapOf(
        "DampingRatioHighBouncy" to Spring.DampingRatioHighBouncy,
        "DampingRatioMediumBouncy" to Spring.DampingRatioMediumBouncy,
        "DampingRatioLowBouncy" to Spring.DampingRatioLowBouncy,
        "DampingRatioNoBouncy" to Spring.DampingRatioNoBouncy,
    )

    val easing = hashMapOf(
        "FastOutSlowInEasing" to FastOutSlowInEasing,
        "FastOutLinearInEasing" to FastOutLinearInEasing,
        "LinearOutSlowInEasing" to LinearOutSlowInEasing,
        "LinearEasing" to LinearEasing,
    )

    fun <T> getSpring(key: String) =
        spring<T>(dampingRatio = spring[key]!!, stiffness = Spring.StiffnessMedium)

    fun <T> getTween(key: String) = tween<T>(DURATION, easing = easing[key]!!)
    fun <T> getFiniteAnimationSpec(type: TypeAnim, key: String): FiniteAnimationSpec<T> {
        return when (type) {
            TypeAnim.Easing -> getTween(key)
            TypeAnim.Spring -> getSpring(key)
        }
    }

    fun getAnimEnterExitGroup(anim: Transition, type: TypeAnim, key: String): EnterExitTransition {
        return when (anim) {
            Transition.SlideVertically -> EnterExitTransition(
                "slideInVertically" to slideInVertically(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    initialOffsetY = { -it }
                ),
                "slideOutVertically" to slideOutVertically(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    targetOffsetY = { -it }
                )
            )

            Transition.SlideHorizontally -> EnterExitTransition(
                "slideInHorizontally" to slideInHorizontally(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    initialOffsetX = { -it }
                ),
                "slideOutHorizontally" to slideOutHorizontally(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    targetOffsetX = { -it }
                )
            )

            Transition.Fade -> EnterExitTransition(
                "fadeIn" to fadeIn(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    initialAlpha = 0f
                ),
                "fadeOut" to fadeOut(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    targetAlpha = 0f
                )
            )

            Transition.Scale -> EnterExitTransition(
                "scaleIn" to scaleIn(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    initialScale = 0f
                ),
                "scaleOut" to scaleOut(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    targetScale = 0f
                )
            )

            Transition.ExpandShrink -> EnterExitTransition(
                "expandIn" to expandIn(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    Alignment.TopEnd
                ),
                "shrinkOut" to shrinkOut(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    Alignment.TopEnd
                )
            )

            Transition.ExpandShrinkHorizontally -> EnterExitTransition(
                "expandHorizontally" to expandHorizontally(
                    animationSpec = getFiniteAnimationSpec(
                        type,
                        key
                    ), Alignment.Start
                ),
                "shrinkHorizontally" to shrinkHorizontally(
                    animationSpec = getFiniteAnimationSpec(
                        type,
                        key
                    ), Alignment.End
                )
            )

            Transition.ExpandShrinkVertically -> EnterExitTransition(
                "expandVertically" to expandVertically(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    Alignment.Top
                ),
                "shrinkVertically" to shrinkVertically(
                    animationSpec = getFiniteAnimationSpec(type, key),
                    Alignment.Bottom
                )
            )
        }
    }

}