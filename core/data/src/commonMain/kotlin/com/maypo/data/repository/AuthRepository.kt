package com.maypo.data.repository

import com.maypo.common.AuthResult
import com.maypo.common.NetworkResult

interface AuthRepository {
    suspend fun getAuthState(): AuthResult
    suspend fun signIn(
        email: String,
    ): NetworkResult<AuthResult>

    suspend fun submitCode(
        code: String,
    ): NetworkResult<AuthResult>

    suspend fun resendCode(): NetworkResult<AuthResult>

    suspend fun signOut(): NetworkResult<AuthResult>
}