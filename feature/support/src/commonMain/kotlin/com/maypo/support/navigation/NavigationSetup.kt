package com.maypo.support.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.maypo.support.SupportHomeScreen

fun EntryProviderScope<NavKey>.supportSection(
    onSubRouteClick: () -> Unit,
) {
    entry<SupportHomeScreen> {
        SupportHomeScreen()
    }
}