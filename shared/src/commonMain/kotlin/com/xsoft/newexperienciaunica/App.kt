package com.xsoft.newexperienciaunica

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.xsoft.newexperienciaunica.navigation.NavigationSetup

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationSetup()
    }
}