@file:OptIn(ExperimentalAnimationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.animation

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dat.jetpackcomposecatalog.core.common.DURATION


abstract class AnimationScreen {
    val listAnimationTransition = MyAnim.Transition.values()
    val listAnimType = MyAnim.TypeAnim.values()

    @Composable
    abstract fun Screen(modifier: Modifier)
}

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
        "LinearOutSlowInEasing" to LinearOutSlowInEasing,
        "FastOutLinearInEasing" to FastOutLinearInEasing,
        "LinearEasing" to LinearEasing,
    )

    fun <T> getSpring(key: String) =
        spring<T>(dampingRatio = spring[key]!!, stiffness = Spring.StiffnessMedium)

    fun <T> getTween(key: String) = tween<T>(DURATION, easing = easing[key]!!)
    fun <T> getAnimationSpec(type: TypeAnim, key: String): FiniteAnimationSpec<T> {
        return when (type) {
            TypeAnim.Easing -> getTween(key)
            TypeAnim.Spring -> getSpring(key)
        }
    }

    fun getAnimEnterExitGroup(anim: Transition, type: TypeAnim, key: String): EnterExitTransition {
        return when (anim) {
            Transition.SlideVertically -> EnterExitTransition(
                "slideInVertically" to slideInVertically(
                    animationSpec = getAnimationSpec(type, key),
                    initialOffsetY = { -it }
                ),
                "slideOutVertically" to slideOutVertically(
                    animationSpec = getAnimationSpec(type, key),
                    targetOffsetY = { -it }
                )
            )

            Transition.SlideHorizontally -> EnterExitTransition(
                "slideInHorizontally" to slideInHorizontally(
                    animationSpec = getAnimationSpec(type, key),
                    initialOffsetX = { -it }
                ),
                "slideOutHorizontally" to slideOutHorizontally(
                    animationSpec = getAnimationSpec(type, key),
                    targetOffsetX = { -it }
                )
            )

            Transition.Fade -> EnterExitTransition(
                "fadeIn" to fadeIn(
                    animationSpec = getAnimationSpec(type, key),
                    initialAlpha = 0f
                ),
                "fadeOut" to fadeOut(
                    animationSpec = getAnimationSpec(type, key),
                    targetAlpha = 0f
                )
            )

            Transition.Scale -> EnterExitTransition(
                "scaleIn" to scaleIn(
                    animationSpec = getAnimationSpec(type, key),
                    initialScale = 0f
                ),
                "scaleOut" to scaleOut(
                    animationSpec = getAnimationSpec(type, key),
                    targetScale = 0f
                )
            )

            Transition.ExpandShrink -> EnterExitTransition(
                "expandIn" to expandIn(
                    animationSpec = getAnimationSpec(type, key),
                    Alignment.TopEnd
                ),
                "shrinkOut" to shrinkOut(
                    animationSpec = getAnimationSpec(type, key),
                    Alignment.TopEnd
                )
            )

            Transition.ExpandShrinkHorizontally -> EnterExitTransition(
                "expandHorizontally" to expandHorizontally(
                    animationSpec = getAnimationSpec(
                        type,
                        key
                    ), Alignment.Start
                ),
                "shrinkHorizontally" to shrinkHorizontally(
                    animationSpec = getAnimationSpec(
                        type,
                        key
                    ), Alignment.End
                )
            )

            Transition.ExpandShrinkVertically -> EnterExitTransition(
                "expandVertically" to expandVertically(
                    animationSpec = getAnimationSpec(type, key),
                    Alignment.Top
                ),
                "shrinkVertically" to shrinkVertically(
                    animationSpec = getAnimationSpec(type, key),
                    Alignment.Bottom
                )
            )
        }
    }

}