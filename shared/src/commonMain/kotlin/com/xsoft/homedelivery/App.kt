package com.xsoft.homedelivery

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.xsoft.homedelivery.navigation.NavigationSetup

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationSetup()
    }
}