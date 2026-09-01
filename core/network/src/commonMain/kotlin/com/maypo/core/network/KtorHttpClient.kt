package com.maypo.core.network

import com.maypo.common.Constants.CORRELATION_ID_HEADER
import com.maypo.konfig.AppKonfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.uuid.Uuid

fun ktorHttpClient( engine: HttpClientEngine): HttpClient = HttpClient(engine) {

    expectSuccess = true

    install(ContentNegotiation){
        json(Json { ignoreUnknownKeys = true })
    }

    defaultRequest {
        url(AppKonfig.baseUrl)
        accept(ContentType.Application.Json)
        contentType(ContentType.Application.Json)
        header(CORRELATION_ID_HEADER, Uuid.random().toString())
    }

    install(Auth) {
        bearer {
        }
    }

    install(Logging){
        logger = Logger.SIMPLE
        level = LogLevel.HEADERS
        sanitizeHeader { header ->
            header.equals(
                HttpHeaders.Authorization,
                ignoreCase = true,
            )
        }
    }

}