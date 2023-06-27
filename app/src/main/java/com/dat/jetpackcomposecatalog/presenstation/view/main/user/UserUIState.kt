package com.dat.jetpackcomposecatalog.presenstation.view.main.user

import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig

sealed interface UserUIState {
    object Loading : UserUIState
    data class Success(val darkMode: DarkThemeConfig) : UserUIState
}
