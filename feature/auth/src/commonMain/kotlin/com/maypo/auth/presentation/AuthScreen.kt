package com.maypo.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maypo.designsystem.component.HdErrorBanner
import com.maypo.designsystem.theme.HdSpacing
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen() {
    val viewModel: AuthViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    AuthContent(
        uiState = uiState,
        onSignIn = { viewModel.signIn("") },
    )
}

@Composable
internal fun AuthContent(
    uiState: AuthUiState,
    onSignIn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(HdSpacing.xxl),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        uiState.errorMessage?.let { message ->
            HdErrorBanner(message = message)
        }
        Button(
            enabled = !uiState.isLoading,
            onClick = onSignIn,
        ) {
            Text(text = if (uiState.isLoading) "Comprobando" else "Iniciar sesión")
        }
    }
}
