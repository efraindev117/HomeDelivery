package com.maypo.domain.auth

enum class AuthStatus {
    CODE_REQUIRED,
    AUTHENTICATED,
    SIGNED_OUT,
    ERROR,
}