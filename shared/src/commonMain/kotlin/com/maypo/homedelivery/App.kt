package com.maypo.homedelivery

import androidx.compose.runtime.Composable
import com.maypo.auth.presentation.AuthScreen
import com.maypo.common.AuthState
import com.maypo.designsystem.theme.HomeDeliveryTheme
import com.maypo.homedelivery.navigation.NavigationSetup

@Composable
fun App(
   // authState: AuthState
) {
    HomeDeliveryTheme {
        NavigationSetup()
        /*
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
         */



    }
}
