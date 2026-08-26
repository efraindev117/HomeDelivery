package com.maypo.auth.state

sealed interface AuthState {
    data object Loading: AuthState
    data object SignedOut: AuthState
    data class SignedIn(val username: String?) : AuthState
}