package com.maypo.auth

data class LoginUiState(
    val email: String = "",
    val code: String = "",
    val isLoading: Boolean = false,
    val requiresCode: Boolean = false,
    val isAuthenticated: Boolean = false,
    val errorMessage: String? = null,
)