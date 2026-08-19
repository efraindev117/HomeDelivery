package com.xsoft.support.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable sealed interface Support : NavKey

@Serializable
data object SupportHomeScreen: Support

