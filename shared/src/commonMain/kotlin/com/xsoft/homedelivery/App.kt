package com.xsoft.homedelivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.xsoft.designsystem.theme.HomeDeliveryTheme
import com.xsoft.homedelivery.navigation.NavigationSetup

@Composable
@Preview
fun App() {
    HomeDeliveryTheme {
        NavigationSetup()
    }
}