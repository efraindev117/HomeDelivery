package com.maypo.support.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maypo.common.ApiError
import com.maypo.common.AppLogger
import com.maypo.common.NetworkResult
import com.maypo.common.toUserMessage
import com.maypo.domain.support.GetSupportContentUseCase
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SupportViewModel(
    private val getSupportContentUseCase: GetSupportContentUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SupportUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadSupport()
    }

    fun loadSupport() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            AppLogger.debug(TAG, "Loading support content")
            try {
                when (val result = getSupportContentUseCase()) {
                    is NetworkResult.Success -> {
                        AppLogger.debug(
                            TAG,
                            "Support content loaded faqs=${result.data.faqs.size}",
                        )
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                content = result.data,
                                errorMessage = null,
                            )
                        }
                    }
                    is NetworkResult.Failure -> {
                        AppLogger.error(TAG, "Support content failed")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.error.toUserMessage(),
                            )
                        }
                    }
                }
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                AppLogger.error(TAG, "Support content unexpected error: ${error.message}")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = ApiError.Unknown.toUserMessage(),
                    )
                }
            }
        }
    }

    fun toggleFaq(faqId: String) {
        _uiState.update { state ->
            val next = if (state.expandedFaqId == faqId) null else faqId
            AppLogger.debug(TAG, "FAQ toggled id=$faqId expanded=${next != null}")
            state.copy(expandedFaqId = next)
        }
    }

    fun onCallSupport() {
        AppLogger.debug(TAG, "Call support tapped")
        _uiState.update { it.copy(callError = null) }
    }

    fun onCallFailed() {
        AppLogger.error(TAG, "Failed to open dialer")
        _uiState.update {
            it.copy(callError = "No se pudo abrir la aplicación de llamadas.")
        }
    }

    private companion object {
        const val TAG = "SupportHome"
    }
}
