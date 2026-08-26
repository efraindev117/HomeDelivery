package com.maypo.auth

import com.maypo.auth.state.AuthState

interface IAuthRepository {
    suspend fun getAuthState(): AuthState

    suspend fun signIn(
        email: String,
    ): SignInResult

    suspend fun submitCode(
        code: String,
    ): SignInResult

    suspend fun resendCode(): SignInResult

    suspend fun signOut()
}

sealed interface SignInResult {

    data object CodeRequired : SignInResult

    data class Success(
        val accessToken: String,
        val idToken: String?,
        val username: String?,
    ) : SignInResult

    data class Error(
        val type: SignInErrorType,
        val message: String? = null,
    ) : SignInResult
}

enum class SignInErrorType {
    USER_NOT_FOUND,
    INVALID_CODE,
    INVALID_CREDENTIALS,
    AUTH_NOT_SUPPORTED,
    BROWSER_REQUIRED,
    UNKNOWN,
}