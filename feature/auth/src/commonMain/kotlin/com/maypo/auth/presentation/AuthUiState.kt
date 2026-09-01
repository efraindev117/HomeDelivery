package com.maypo.auth.presentation

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
