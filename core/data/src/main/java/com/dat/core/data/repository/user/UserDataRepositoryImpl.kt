package com.dat.core.data.repository.user


import com.dat.core.datastore.PreferenceDataSource
import com.dat.core.model.DarkThemeConfig
import com.dat.core.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    UserDataRepository {

    override val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        preferenceDataSource.updateDarkMode(darkThemeConfig)
    }
}