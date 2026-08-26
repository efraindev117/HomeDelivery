package com.maypo.homedelivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maypo.designsystem.theme.HomeDeliveryTheme
import com.maypo.homedelivery.navigation.NavigationSetup

@Composable
@Preview
fun App() {
    HomeDeliveryTheme {
        NavigationSetup()
    }
}