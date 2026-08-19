package com.xsoft.my_routes.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.xsoft.my_routes.RoutesHomeScreen

fun EntryProviderScope<NavKey>.myRoutesSection(
    onSubRouteClick: () -> Unit,
) {
    entry<HomeRoute> {
        RoutesHomeScreen()
    }
}