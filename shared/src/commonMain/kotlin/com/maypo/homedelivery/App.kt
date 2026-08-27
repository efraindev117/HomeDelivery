package com.maypo.homedelivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maypo.auth.AuthScreen
import com.maypo.common.AuthState
import com.maypo.designsystem.theme.HomeDeliveryTheme
import com.maypo.homedelivery.navigation.NavigationSetup

@Composable
fun App(
    authState: AuthState
) {
    HomeDeliveryTheme {
        when( authState) {
            AuthState.Loading -> {
                //loader
            }
            AuthState.Authenticated -> {
                NavigationSetup()
            }
            AuthState.Unauthenticated -> {
                AuthScreen()
            }
        }
    }
}