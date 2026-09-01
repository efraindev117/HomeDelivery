package com.maypo.support.presentation

import com.maypo.common.support.SupportContent

data class SupportUiState(
    val isLoading: Boolean = false,
    val content: SupportContent? = null,
    val errorMessage: String? = null,
    val expandedFaqId: String? = null,
    val callError: String? = null,
)
