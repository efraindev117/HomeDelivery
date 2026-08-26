package com.maypo.history.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.maypo.history.HistoryHomeScreen

fun EntryProviderScope<NavKey>.historySection(
    onSubRouteClick: () -> Unit,
) {
    entry<HomeHistory> {
        HistoryHomeScreen()
    }
}