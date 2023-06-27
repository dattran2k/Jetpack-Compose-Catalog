package com.dat.jetpackcomposecatalog.core.datastore

import androidx.datastore.core.DataStore
import com.dat.jetpackcomposecatalog.DarkThemeConfigProto
import com.dat.jetpackcomposecatalog.UserPreferences
import com.dat.jetpackcomposecatalog.copy
import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig
import com.dat.jetpackcomposecatalog.data.model.local.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(private val userPreferences: DataStore<UserPreferences>) {
    val userData: Flow<UserData> = userPreferences.data.map {
        UserData(
            darkThemeConfig = when (it.darkThemeConfig) {
                null,
                DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                DarkThemeConfigProto.UNRECOGNIZED,
                DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                -> DarkThemeConfig.FOLLOW_SYSTEM

                DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT ->
                    DarkThemeConfig.LIGHT

                DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
            },
        )
    }

    suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        userPreferences.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM ->
                        DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM

                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }
}