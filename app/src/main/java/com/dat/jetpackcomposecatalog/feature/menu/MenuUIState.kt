package com.dat.jetpackcomposecatalog.feature.menu

sealed interface MenuUIState {
    object Loading : MenuUIState
    data class Success(val darkMode: com.dat.core.model.DarkThemeConfig) : MenuUIState
}
