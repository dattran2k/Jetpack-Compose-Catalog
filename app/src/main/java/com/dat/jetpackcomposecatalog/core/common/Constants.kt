@file:OptIn(ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.core.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.ui.Alignment

object DataConst {
    val listVerticalArrangement: List<Pair<String, Arrangement.Vertical>> = listOf(
        "Arrangement.Top" to Arrangement.Top,
        "Arrangement.Center" to Arrangement.Center,
        "Arrangement.Bottom" to Arrangement.Bottom,
        "Arrangement.SpaceEvenly" to Arrangement.SpaceEvenly,
        "Arrangement.SpaceAround" to Arrangement.SpaceAround,
        "Arrangement.SpaceBetween" to Arrangement.SpaceBetween,
    )
    val listVerticalAlignment: List<Pair<String, Alignment.Vertical>> = listOf(
        "Alignment.Start" to Alignment.Top,
        "Alignment.End" to Alignment.Bottom,
        "Alignment.CenterHorizontally" to Alignment.CenterVertically,
    )

    val listHorizontalArrangement: List<Pair<String, Arrangement.Horizontal>> = listOf(
        "Arrangement.Start" to Arrangement.Start,
        "Arrangement.Center" to Arrangement.Center,
        "Arrangement.End" to Arrangement.End,
        "Arrangement.SpaceEvenly" to Arrangement.SpaceEvenly,
        "Arrangement.SpaceAround" to Arrangement.SpaceAround,
        "Arrangement.SpaceBetween" to Arrangement.SpaceBetween,
    )
    val listHorizontalAlignment: List<Pair<String, Alignment.Horizontal>> = listOf(
        "Alignment.Start" to Alignment.Start,
        "Alignment.End" to Alignment.End,
        "Alignment.CenterHorizontally" to Alignment.CenterHorizontally,
    )

    val listStaggeredGridCells: List<Pair<String, Class<out StaggeredGridCells>>> = listOf(
        "StaggeredGridCells.Adaptive" to StaggeredGridCells.Adaptive::class.java,
        "StaggeredGridCells.Fixed" to StaggeredGridCells.Fixed::class.java,

        )
}

object TestTag {
    const val CIRCULAR_PROGRESS_INDICATOR_TAG = "CIRCULAR_PROGRESS_INDICATOR_TAG"
    const val CIRCULAR_PROGRESS_INDICATOR_TAG_2 = "CIRCULAR_PROGRESS_INDICATOR_TAG_2"
    const val LAZY_COLUMN_TAG = "LAZY_COLUMN_TAG"
}