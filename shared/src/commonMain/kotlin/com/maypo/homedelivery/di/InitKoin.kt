package com.maypo.homedelivery.di

import com.maypo.common.AppLogger
import com.maypo.konfig.AppKonfig
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    config: KoinAppDeclaration? = null
): KoinApplication {
    AppLogger.configure(enabled = AppKonfig.loggingEnabled)
    AppLogger.debug(
        tag = "App",
        message = "flavor=${AppKonfig.environment} logging=${AppKonfig.loggingEnabled}",
    )
    return startKoin {
        config?.invoke(this)
        modules(
            appModule,
        )
    }
}