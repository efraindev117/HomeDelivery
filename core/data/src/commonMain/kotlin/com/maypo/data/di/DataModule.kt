package com.maypo.data.di

import com.maypo.common.dispatchers.dispatcherModule
import com.maypo.core.network.di.networkModule
import com.maypo.data.datasource.LocalSupportDataSource
import com.maypo.data.datasource.SupportDataSource
import com.maypo.data.repository.AuthRepository
import com.maypo.data.repository.AuthRepositoryImpl
import com.maypo.data.repository.SupportRepository
import com.maypo.data.repository.SupportRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
   includes(
       dispatcherModule,
       networkModule
   )
    single<AuthRepository>{
        AuthRepositoryImpl(networkAuthDataSource = get())
    }
    // Sustituir LocalSupportDataSource por la implementación de red cuando exista el endpoint.
    single<SupportDataSource> { LocalSupportDataSource() }
    single<SupportRepository> {
        SupportRepositoryImpl(supportDataSource = get())
    }
}