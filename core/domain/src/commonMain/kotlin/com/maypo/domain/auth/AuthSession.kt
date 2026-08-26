package com.maypo.domain.auth

data class AuthSession(
    val accessToken: String,
    val idToken: String?,
    val username: String?,
)
