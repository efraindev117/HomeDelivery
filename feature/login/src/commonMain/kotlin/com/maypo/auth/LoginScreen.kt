package com.maypo.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    state: LoginUiState,
) {
    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }

        state.isAuthenticated -> {
            Text(
                text = "Sesión iniciada: ${state.username.orEmpty()}",
            )
        }

        else -> {
            Column {

                Text(
                    text = "Inicia sesión",
                )
            }
        }
    }
}