package com.xsoft.login.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Authentication : NavKey

@kotlinx.serialization.Serializable
data object LoginRoute : Authentication