package com.dat.jetpackcomposecatalog.presenstation.theme

import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig
import com.dat.jetpackcomposecatalog.presenstation.MainActivityUiState

@Composable
fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    MainActivityUiState.Loading -> isSystemInDarkTheme()
    is MainActivityUiState.Success -> when (uiState.userData.darkThemeConfig) {
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}

data class MyColorPalette(
    val backGround: Color,
    val textTitle: Color,
    val todoItemBackGround: Color
)

val LightTheme = MyColorPalette(
    backGround = WhiteColor,
    textTitle = BlackColor,
    todoItemBackGround = LineColor
)
val DarkTheme = MyColorPalette(
    backGround = BlackColor,
    textTitle = WhiteColor,
    todoItemBackGround = WhiteColorAlpha10
)

val LocalCustomColorTheme = compositionLocalOf {
    LightTheme
}
private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,

    )

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200
)


@Composable
fun JetpackComposeCatalogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val colorsCustom = if (darkTheme) {
        DarkTheme
    } else {
        LightTheme
    }
    CompositionLocalProvider(LocalCustomColorTheme provides colorsCustom) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography.copy(
                bodySmall = Typography.bodySmall.copy(
                    color = LocalCustomColorTheme.current.textTitle
                ),
                bodyMedium = Typography.bodySmall.copy(
                    color = LocalCustomColorTheme.current.textTitle
                ),
                bodyLarge = Typography.bodySmall.copy(
                    color = LocalCustomColorTheme.current.textTitle
                ),
            ),
            shapes = Shapes,
            content = content,
        )
    }
}
