package com.maypo.homedelivery.di

import com.maypo.auth.di.authViewModelModule
import com.maypo.data.di.dataModule
import com.maypo.domain.di.domainModule
import com.maypo.support.di.supportViewModelModule

val appModule = listOf(
    dataModule,
    domainModule,
    authViewModelModule,
    supportViewModelModule,
)
