package com.dat.jetpackcomposecatalog.data.respository.user


import com.dat.jetpackcomposecatalog.core.datastore.PreferenceDataSource
import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig
import com.dat.jetpackcomposecatalog.data.model.local.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    UserDataRepository {

    override val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        preferenceDataSource.updateDarkMode(darkThemeConfig)
    }
}