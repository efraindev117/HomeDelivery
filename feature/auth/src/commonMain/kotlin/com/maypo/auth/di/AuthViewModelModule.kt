package com.maypo.auth.di

import com.maypo.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::AuthViewModel)
}