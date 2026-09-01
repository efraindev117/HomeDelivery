package com.maypo.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maypo.common.AppLogger
import com.maypo.common.AuthResult
import com.maypo.common.NetworkResult
import com.maypo.common.toUserMessage
import com.maypo.domain.auth.ResendCodeUseCase
import com.maypo.domain.auth.SignInUseCase
import com.maypo.domain.auth.SignOutUseCase
import com.maypo.domain.auth.SubmitCodeUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val submitCodeUseCase: SubmitCodeUseCase,
    private val resendCodeUseCase: ResendCodeUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    private val _authResult = MutableSharedFlow<NetworkResult<AuthResult>>()
    val authResult = _authResult.asSharedFlow()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun signIn(email: String) {
        execute { signInUseCase(email.trim()) }
    }

    fun submitCode(code: String) {
        execute { submitCodeUseCase(code.trim()) }
    }

    fun resendCode() {
        execute { resendCodeUseCase() }
    }

    fun signOut() {
        execute { signOutUseCase() }
    }

    private fun execute(action: suspend () -> NetworkResult<AuthResult>) = viewModelScope.launch {
        _uiState.value = AuthUiState(isLoading = true)
        try {
            val result = action()
            if (result is NetworkResult.Failure) {
                AppLogger.error(
                    tag = "Auth",
                    message = "authentication operation failed",
                )
                _uiState.value = AuthUiState(errorMessage = result.error.toUserMessage())
            }
            _authResult.emit(result)
        } finally {
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }
}
