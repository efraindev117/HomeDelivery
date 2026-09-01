package com.maypo.core.network.di

import com.maypo.core.network.client.createSecureHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module = module {
    single<HttpClient> {
        createSecureHttpClient(engine = Darwin.create())
    }
}
