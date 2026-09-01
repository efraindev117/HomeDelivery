package com.maypo.data.repository

import com.maypo.common.AuthResult
import com.maypo.common.NetworkResult
import com.maypo.core.network.repository.INetworkAuthDataSource

class AuthRepositoryImpl(
    private val networkAuthDataSource: INetworkAuthDataSource) : AuthRepository {

    override suspend fun getAuthState(): AuthResult =
        networkAuthDataSource.getAuthState()

    override suspend fun signIn(email: String): NetworkResult<AuthResult> =
        networkAuthDataSource.signIn(email)

    override suspend fun submitCode(code: String, ): NetworkResult<AuthResult> =
        networkAuthDataSource.submitCode(code)

    override suspend fun resendCode(): NetworkResult<AuthResult> =
        networkAuthDataSource.resendCode()

    override suspend fun signOut(): NetworkResult<AuthResult> =
        networkAuthDataSource.signOut()
}