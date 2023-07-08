package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.foundation.lazy.grid.GridCells

enum class MyGridCells(val value: Class<out GridCells>, val typeName: String) {
    Adaptive(GridCells.Adaptive::class.java, "GridCells.Adaptive"),
    Fixed(GridCells.Fixed::class.java, "GridCells.Fixed")
}