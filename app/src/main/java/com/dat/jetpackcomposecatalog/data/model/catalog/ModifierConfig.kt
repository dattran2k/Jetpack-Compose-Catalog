package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ModifierConfig(val composeName: String, val listShowCode: List<ShowCode>)
sealed class Padding(listShowCode: List<ShowCode>) : ModifierConfig("Padding", listShowCode)
class PaddingAll(val size: Dp = 0.dp) : Padding(listOf(ShowCode("all", size.toString())))
class PaddingHorVer(val horizontal: Dp = 0.dp, val vertical: Dp = 0.dp) :
    Padding(
        listOf(
            ShowCode("horizontal", horizontal.toString()),
            ShowCode("vertical", vertical.toString())
        )
    )

class PaddingWay(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
) : Padding(
    listOf(
        ShowCode("start", start.toString()),
        ShowCode("top", top.toString()),
        ShowCode("end", end.toString()),
        ShowCode("bottom", bottom.toString())
    )
)

@Stable
fun Modifier.updateModifier(modifierConfig: ModifierConfig) = this.then(
    when (modifierConfig) {
        is PaddingAll -> Modifier.padding(modifierConfig.size)
        is PaddingHorVer -> Modifier.padding(modifierConfig.horizontal, modifierConfig.vertical)
        is PaddingWay -> Modifier.padding(
            modifierConfig.start, modifierConfig.top, modifierConfig.end, modifierConfig.bottom
        )
    }
)