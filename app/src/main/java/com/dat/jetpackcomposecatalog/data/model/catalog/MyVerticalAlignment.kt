package com.dat.jetpackcomposecatalog.data.model.catalog

import androidx.compose.ui.Alignment

enum class MyVerticalAlignment(val value: Alignment.Vertical, val typeName: String) {
    Top(Alignment.Top, "Alignment.Top"),
    Bottom(Alignment.Bottom, "Alignment.Bottom"),
    CenterVertically(Alignment.CenterVertically, "Alignment.CenterVertically"), ;
}