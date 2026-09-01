package com.maypo.data.di

import com.maypo.common.dispatchers.dispatcherModule
import com.maypo.core.network.di.networkModule
import com.maypo.data.repository.AuthRepository
import com.maypo.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
   includes(
       dispatcherModule,
       networkModule
   )
    single<AuthRepository>{
        AuthRepositoryImpl(networkAuthDataSource = get())
    }
}