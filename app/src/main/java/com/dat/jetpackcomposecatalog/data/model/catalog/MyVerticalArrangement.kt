package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

enum class MyVerticalArrangement(val value: Arrangement.Vertical, val typeName: String) {
    Top(Arrangement.Top, "Arrangement.Top"),
    Center(Arrangement.Center, "Arrangement.Center"),
    Bottom(Arrangement.Bottom, "Arrangement.Bottom"),
    SpaceEvenly(Arrangement.SpaceEvenly, "Arrangement.SpaceEvenly"),
    SpaceAround(Arrangement.SpaceAround, "Arrangement.SpaceAround"),
    SpaceBetween(Arrangement.SpaceBetween, "Arrangement.SpaceBetween"),
    AlignedCenterVertically(
        Arrangement.aligned(Alignment.CenterVertically),
        "Arrangement.aligned(Alignment.CenterVertically)"
    ),
    AlignedTop(
        Arrangement.aligned(Alignment.Top),
        "Arrangement.aligned(Alignment.Top)"
    ),
    AlignedBottom(
        Arrangement.aligned(Alignment.Top),
        "Arrangement.aligned(Alignment.Top)"
    ),
    SpacedBy(
        Arrangement.spacedBy(20.dp),
        "Arrangement.spacedBy(20.dp)"
    ),
    SpacedByCenterVertically(
        Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        "Arrangement.spacedBy(20.dp, Alignment.CenterVertically)"
    ),
    SpacedByTop(
        Arrangement.spacedBy(20.dp, Alignment.Top),
        "Arrangement.spacedBy(20.dp, Alignment.Top)"
    ),
    SpacedByBottom(
        Arrangement.spacedBy(20.dp, Alignment.Bottom),
        "Arrangement.spacedBy(20.dp, Alignment.Bottom)"
    ), ;

}