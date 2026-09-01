package com.maypo.domain.di

import com.maypo.domain.auth.AuthUsesCase
import com.maypo.domain.support.GetSupportContentUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        AuthUsesCase(
            sigIn = get(),
            submitCode = get(),
            resendCode = get(),
            signOut = get()
        )
    }
    factory { GetSupportContentUseCase(supportRepository = get()) }
}