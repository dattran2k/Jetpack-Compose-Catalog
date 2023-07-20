package com.dat.core.data.repository.user

import com.dat.core.model.DarkThemeConfig
import com.dat.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig)

}