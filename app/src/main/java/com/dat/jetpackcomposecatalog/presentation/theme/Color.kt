package com.dat.jetpackcomposecatalog.presentation.theme

import androidx.compose.ui.graphics.Color

val LineColor = Color(0xFFDDDDDD)
val YellowCodeColor = Color(0xFFFFC66D)
val OrangeCodeColor = Color(0xFFCC7832)
val BlueCodeColor = Color(0xFF467CDA)
val WhiteCodeColor = Color(0xFFA9B7C6)
val BlackColor = Color(0xFF000000)
val BlackCodeColor = Color(0xFF2B2B2B)
val WhiteColor = Color(0xFFFFFFFF)
val WhiteColorAlpha10 = Color(0x1AFFFFFF)
val PrimaryColor = Color(0xFF1F9FFC)

data class MyColorPalette(
    val background: Color,
    val textTitle: Color,
    val todoItemBackGround: Color,
)

val LightTheme = MyColorPalette(
    background = WhiteColor,
    textTitle = BlackColor,
    todoItemBackGround = LineColor
)
val DarkTheme = MyColorPalette(
    background = BlackColor,
    textTitle = WhiteColor,
    todoItemBackGround = WhiteColorAlpha10
)

