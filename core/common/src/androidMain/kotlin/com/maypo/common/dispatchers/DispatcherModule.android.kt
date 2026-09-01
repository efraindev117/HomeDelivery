package com.maypo.common.dispatchers

import org.koin.core.module.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val dispatcherModule: Module = module {

    single<CoroutineDispatcher>(
        qualifier = named<IoDispatcher>(),
    ) {
        Dispatchers.IO
    }

    single<CoroutineDispatcher>(
        qualifier = named<DefaultDispatcher>(),
    ) {
        Dispatchers.Default
    }
}