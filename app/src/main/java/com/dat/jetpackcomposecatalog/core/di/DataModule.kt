package com.dat.jetpackcomposecatalog.core.di

import com.dat.jetpackcomposecatalog.data.respository.user.UserDataRepository
import com.dat.jetpackcomposecatalog.data.respository.user.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl,
    ): UserDataRepository

}