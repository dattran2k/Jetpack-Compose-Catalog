package com.dat.ui.common.ui_model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells

@OptIn(ExperimentalFoundationApi::class)
enum class MyStaggeredGridCells(val value: Class<out StaggeredGridCells>, val typeName: String) {
    Adaptive(StaggeredGridCells.Adaptive::class.java, "StaggeredGridCells.Adaptive"),
    Fixed(StaggeredGridCells.Fixed::class.java, "StaggeredGridCells.Fixed");
}