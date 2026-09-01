package com.maypo.support.di

import com.maypo.support.presentation.SupportViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val supportViewModelModule = module {
    viewModelOf(::SupportViewModel)
}
