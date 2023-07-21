package com.dat.ui.common.ui_model

import androidx.compose.ui.Alignment

enum class MyHorizontalAlignment(val value: Alignment.Horizontal, val typeName: String) {
    Start(Alignment.Start, "Alignment.Start"),
    Bottom(Alignment.CenterHorizontally, "Alignment.CenterHorizontally"),
    CenterVertically(Alignment.End, "Alignment.End"), ;
}