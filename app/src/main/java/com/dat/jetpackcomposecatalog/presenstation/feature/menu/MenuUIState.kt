package com.dat.jetpackcomposecatalog.presenstation.feature.menu

import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig

sealed interface MenuUIState {
    object Loading : MenuUIState
    data class Success(val darkMode: DarkThemeConfig) : MenuUIState
}
