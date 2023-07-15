package com.dat.jetpackcomposecatalog.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColorTheme = staticCompositionLocalOf { LightTheme }
val LocalIsDarkMode = staticCompositionLocalOf { false }
val DarkColorPalette = darkColorScheme(primary = PrimaryColor)
val LightColorPalette = lightColorScheme(primary = PrimaryColor)

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
    CompositionLocalProvider(
        LocalCustomColorTheme provides colorsCustom,
        LocalIsDarkMode provides darkTheme
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content,
        )
    }
}
