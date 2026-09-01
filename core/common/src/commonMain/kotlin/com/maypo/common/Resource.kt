package com.maypo.common

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T,
    ) : NetworkResult<T>

    data class Failure(
        val error: Throwable,
    ) : NetworkResult<Nothing>
}

sealed interface AuthState {

    data object Loading : AuthState

    data object Authenticated : AuthState

    data object Unauthenticated : AuthState
}

sealed interface AuthResult {

    data object CodeRequired : AuthResult

    data object Authenticated : AuthResult

    data object CodeResent : AuthResult

    data object SignedOut : AuthResult
}