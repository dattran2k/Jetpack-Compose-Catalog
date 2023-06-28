package com.dat.jetpackcomposecatalog.presenstation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColorTheme = staticCompositionLocalOf { LightTheme }
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
    CompositionLocalProvider(LocalCustomColorTheme provides colorsCustom) {
        val typography = Typography.copy(
            bodySmall = Typography.bodySmall.copy(
                color = LocalCustomColorTheme.current.textTitle
            ),
            bodyMedium = Typography.bodyMedium.copy(
                color = LocalCustomColorTheme.current.textTitle
            ),
            bodyLarge = Typography.bodyLarge.copy(
                color = LocalCustomColorTheme.current.textTitle
            ),
        )
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            content = content,
        )
    }
}
