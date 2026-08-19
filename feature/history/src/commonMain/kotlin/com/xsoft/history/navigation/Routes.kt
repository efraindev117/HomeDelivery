package com.xsoft.history.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable sealed interface History : NavKey

@Serializable
data class HomeHistory(val id: String) : History

@Serializable
data class HistoryDetail(val id: String): History

