package com.maypo.auth.di

import com.maypo.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::AuthViewModel)
}