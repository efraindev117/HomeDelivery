package com.maypo.konfig

object AppKonfig {

    val environment: String
        get() = BuildKonfig.environment

    val baseUrl: String
        get() = BuildKonfig.baseUrl
}