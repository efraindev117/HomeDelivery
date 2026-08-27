package com.maypo.homedelivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maypo.common.AuthResult
import com.maypo.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _authState = MutableStateFlow<AuthResult?>(null)
    val authState = _authState.asStateFlow()

    init {
        checkAuthentication()
    }

    private fun checkAuthentication() {
        viewModelScope.launch {
            _authState.value = authRepository.getAuthState()
        }
    }

}