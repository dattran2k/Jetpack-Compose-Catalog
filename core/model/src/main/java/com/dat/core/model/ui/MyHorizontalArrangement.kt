package com.dat.core.model.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

enum class MyHorizontalArrangement(val value: Arrangement.Horizontal, val typeName: String) {
    Start(
        Arrangement.Start,
        "Arrangement.Start"
    ),
    Center(
        Arrangement.Center,
        "Arrangement.Center"
    ),
    End(
        Arrangement.End,
        "Arrangement.End"
    ),
    SpaceEvenly(
        Arrangement.SpaceEvenly,
        "Arrangement.SpaceEvenly"
    ),
    SpaceAround(
        Arrangement.SpaceAround,
        "Arrangement.SpaceAround"
    ),
    SpaceBetween(
        Arrangement.SpaceBetween,
        "Arrangement.SpaceBetween"
    ),
    AlignedCenterHorizontally(
        Arrangement.aligned(Alignment.CenterHorizontally),
        "Arrangement.aligned(Alignment.CenterHorizontally)"
    ),
    AlignedStart(
        Arrangement.aligned(Alignment.Start),
        "Arrangement.aligned(Alignment.Start)"
    ),
    AlignedEnd(
        Arrangement.aligned(Alignment.End),
        "Arrangement.aligned(Alignment.End)"
    ),
    SpacedBy(
        Arrangement.spacedBy(20.dp),
        "Arrangement.spacedBy(20.dp)"
    ),
    SpacedByCenterHorizontally(
        Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        "Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)"
    ),
    SpacedByStart(
        Arrangement.spacedBy(20.dp, Alignment.Start),
        "Arrangement.spacedBy(20.dp, Alignment.Start)"
    ),
    SpacedByEnd(
        Arrangement.spacedBy(20.dp, Alignment.End),
        "Arrangement.spacedBy(20.dp, Alignment.End)"
    ),
}