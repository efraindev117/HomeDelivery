package com.maypo.domain.auth

import com.maypo.common.AuthResult
import com.maypo.common.NetworkResult
import com.maypo.data.repository.AuthRepository

class ResendCodeUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): NetworkResult<AuthResult> =
        authRepository.resendCode()
}
