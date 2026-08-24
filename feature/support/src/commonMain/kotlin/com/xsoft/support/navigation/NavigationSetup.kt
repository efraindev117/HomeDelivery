package com.xsoft.support.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.xsoft.support.SupportHomeScreen

fun EntryProviderScope<NavKey>.supportSection() {
    entry<SupportHomeScreen> {
        SupportHomeScreen()
    }
}