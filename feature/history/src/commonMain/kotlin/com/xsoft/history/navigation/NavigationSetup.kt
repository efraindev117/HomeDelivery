package com.xsoft.history.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.xsoft.history.HistoryHomeScreen

fun EntryProviderScope<NavKey>.historySection() {
    entry<HomeHistory> {
        HistoryHomeScreen()
    }
}