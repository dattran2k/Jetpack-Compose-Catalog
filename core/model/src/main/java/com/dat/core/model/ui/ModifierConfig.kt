package com.dat.core.model.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.UUID

sealed class ModifierConfig(
    val composeName: String,
    val listShowCode: List<ShowCode>,
    val key : UUID = UUID.randomUUID()
)
@Stable
fun Modifier.getModifierFromConfig(modifierConfig: ModifierConfig) = this.then(
    when (modifierConfig) {
        is ModPadding -> when (modifierConfig) {
            is ModPadding.All -> Modifier.padding(modifierConfig.size)
            is ModPadding.HorVer -> Modifier.padding(
                modifierConfig.horizontal,
                modifierConfig.vertical
            )

            is ModPadding.Way -> Modifier.padding(
                modifierConfig.start, modifierConfig.top, modifierConfig.end, modifierConfig.bottom
            )
        }

        is ModSize -> when (modifierConfig) {
            is ModSize.SizeAll -> Modifier.size(modifierConfig.size)
            is ModSize.WidthHeight -> Modifier.size(
                width = modifierConfig.width,
                height = modifierConfig.height
            )
        }
    }
)
operator fun Modifier.plus(modifier: Modifier) : Modifier = this.then(modifier)

sealed class ModPadding(listShowCode: List<ShowCode>) : ModifierConfig("Padding", listShowCode) {
    //  Modifier.padding(all = 0.dp)
    class All(val size: Dp = 0.dp) : ModPadding(listOf(ShowCode("all", size.toString())))

    //  Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
    class HorVer(val horizontal: Dp = 0.dp, val vertical: Dp = 0.dp) :
        ModPadding(
            listOf(
                ShowCode("horizontal", horizontal.toString()),
                ShowCode("vertical", vertical.toString())
            )
        )

    // Modifier.padding(start = 0.dp, top = 0.dp,end = 0.dp, bottom = 0.dp)
    class Way(
        val start: Dp = 0.dp,
        val top: Dp = 0.dp,
        val end: Dp = 0.dp,
        val bottom: Dp = 0.dp,
    ) : ModPadding(
        listOf(
            ShowCode("start", start.toString()),
            ShowCode("top", top.toString()),
            ShowCode("end", end.toString()),
            ShowCode("bottom", bottom.toString())
        )
    )
}

sealed class ModSize(listShowCode: List<ShowCode>) : ModifierConfig("Size", listShowCode) {
    val modifier = Modifier.size(size = 0.dp)
    // Modifier.size(size = 0.dp)
    class SizeAll(val size: Dp = 0.dp) : ModSize(listOf(ShowCode("size", size.toString())))

    //  Modifier.size(width = 0.dp, height = 0.dp)
    class WidthHeight(val width: Dp = 0.dp, val height: Dp = 0.dp) :
        ModSize(
            listOf(
                ShowCode("width", width.toString()),
                ShowCode("height", height.toString())
            )
        )
}
// TODO more and more and more