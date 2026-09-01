package com.maypo.core.network

import com.maypo.common.AuthResult
import com.maypo.common.ApiError
import com.maypo.common.AppLogger
import com.maypo.common.NetworkResult
import com.maypo.core.network.repository.INetworkAuthDataSource
import com.microsoft.identity.nativeauth.INativeAuthPublicClientApplication
import com.microsoft.identity.nativeauth.parameters.NativeAuthSignInParameters
import com.microsoft.identity.nativeauth.statemachine.errors.SignInError
import com.microsoft.identity.nativeauth.statemachine.results.SignInResult

class MsalNetworkAuthDataSource(private val authClient: INativeAuthPublicClientApplication)
    : INetworkAuthDataSource {

    private var codeRequiredResult: SignInResult.CodeRequired? = null

    override suspend fun getAuthState(): AuthResult {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(
        email: String,
    ): NetworkResult<AuthResult> {

        val parameters = NativeAuthSignInParameters(
            username = email,
        )

        return when (val result = authClient.signIn(parameters)) {

            is SignInResult.CodeRequired -> {
                codeRequiredResult = result

                NetworkResult.Success(
                    AuthResult.CodeRequired,
                )
            }

            is SignInResult.Complete -> {
                codeRequiredResult = null

                NetworkResult.Success(
                    AuthResult.Authenticated,
                )
            }

            is SignInError -> {
                codeRequiredResult = null
                AppLogger.error(
                    tag = "Auth",
                    message = "MSAL sign-in failed",
                )

                NetworkResult.Failure(
                    error = ApiError.Authentication(reason = "msal_sign_in"),
                )
            }

            else -> {
                codeRequiredResult = null
                AppLogger.error(
                    tag = "Auth",
                    message = "unrecognized MSAL sign-in result",
                )

                NetworkResult.Failure(
                    error = ApiError.Unknown,
                )
            }
        }
    }

    override suspend fun submitCode(code: String): NetworkResult<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun resendCode(): NetworkResult<AuthResult> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): NetworkResult<AuthResult> {
        TODO("Not yet implemented")
    }

}