package com.maypo.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.koin.core.logger.Logger
import org.koin.core.module.Module
import kotlin.uuid.Uuid

expect val networkModule: Module

