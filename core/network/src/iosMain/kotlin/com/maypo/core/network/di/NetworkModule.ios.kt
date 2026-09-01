package com.maypo.core.network.di

import com.maypo.core.network.ktorHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module = module {
    single<HttpClient> {
        ktorHttpClient(
            engine = Darwin.create(),
        )
    }
}