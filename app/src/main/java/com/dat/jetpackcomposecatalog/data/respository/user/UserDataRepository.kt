package com.dat.jetpackcomposecatalog.data.respository.user

import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig
import com.dat.jetpackcomposecatalog.data.model.local.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    suspend fun updateDarkMode(darkThemeConfig : DarkThemeConfig)

}