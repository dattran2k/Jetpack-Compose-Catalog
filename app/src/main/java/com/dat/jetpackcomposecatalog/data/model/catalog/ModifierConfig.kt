package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ModifierConfig
sealed class Padding : ModifierConfig()
class PaddingAll(val size: Dp = 0.dp) : Padding()
class PaddingHorVer(val horizontal: Dp = 0.dp, val vertical: Dp = 0.dp) : Padding()
class PaddingWay(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp
) : Padding()

@Stable
fun Modifier.updateModifier(modifierConfig: ModifierConfig) = this.then(
    when (modifierConfig) {
        is PaddingAll -> Modifier.padding(modifierConfig.size)
        is PaddingHorVer -> Modifier.padding(modifierConfig.horizontal, modifierConfig.vertical)
        is PaddingWay -> Modifier.padding(
            modifierConfig.start,
            modifierConfig.top,
            modifierConfig.end,
            modifierConfig.bottom
        )
    }
)