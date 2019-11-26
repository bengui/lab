package com.benguiman.lab.di

import com.benguiman.lab.domain.UserManager
import com.benguiman.lab.domain.UserManagerImpl
import com.benguiman.lab.network.UserApi
import com.benguiman.lab.network.UserApiImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationBindModule {

    @Binds
    abstract fun bindUserApiImpl(userApiImpl: UserApiImpl): UserApi

    @Binds
    abstract fun bindUserManagerImpl(userManagerImpl: UserManagerImpl): UserManager
}