package com.dat.ui.feature.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.core.data.repository.user.UserDataRepository
import com.dat.core.model.DarkThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {
    val menuUIState: StateFlow<MenuUIState> = userDataRepository.userData.map { userData ->
        MenuUIState.Success(userData.darkThemeConfig)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MenuUIState.Loading,
    )

    fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.updateDarkMode(darkThemeConfig)
        }
    }
}

