package com.dat.core.data.di

import com.dat.core.data.repository.user.UserDataRepository
import com.dat.core.data.repository.user.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl,
    ): UserDataRepository

}