package com.maypo.domain.auth

sealed interface AuthState {

    data object SignedOut : AuthState

    data class SignedIn(
        val username: String?,
    ) : AuthState
}