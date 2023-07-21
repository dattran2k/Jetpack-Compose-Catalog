package com.dat.ui.feature.menu

import com.dat.core.model.DarkThemeConfig

sealed interface MenuUIState {
    object Loading : MenuUIState
    data class Success(val darkMode: DarkThemeConfig) : MenuUIState
}
