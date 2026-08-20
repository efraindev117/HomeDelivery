package com.xsoft.homedelivery.navigation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.xsoft.designsystem.component.HdBottomNav
import com.xsoft.designsystem.component.HdBottomNavItem
import com.xsoft.designsystem.theme.HdColors
import com.xsoft.homedelivery.navigation.state.TOP_LEVEL_ROUTES
import com.xsoft.homedelivery.navigation.state.rememberNavigationState
import com.xsoft.homedelivery.navigation.state.toEntries
import com.xsoft.my_routes.navigation.HomeRoute
import com.xsoft.history.navigation.historySection
import com.xsoft.my_routes.navigation.myRoutesSection
import com.xsoft.support.navigation.supportSection

@Composable
fun NavigationSetup() {
    val navigationState = rememberNavigationState(
        startRoute = HomeRoute,
        topLevelRoutes = TOP_LEVEL_ROUTES.keys
    )
    val navigator = remember { Navigator(navigationState) }

    val entryProvider = entryProvider {
        myRoutesSection(onSubRouteClick = {})
        historySection(onSubRouteClick = {})
        supportSection(onSubRouteClick = {})
    }

    Scaffold(
        containerColor = HdColors.canvas,
        bottomBar = {
        HdBottomNav(
            items = TOP_LEVEL_ROUTES.map { (key, value) ->
                HdBottomNavItem(
                    key = key,
                    label = value.title,
                    icon = value.icon,
                )
            },
            selectedKey = navigationState.topLevelRoute,
            onItemSelected = { navigator.navigate(it) },
        )
    }) { paddingValues ->
        NavDisplay(
            entries = navigationState.toEntries(entryProvider),
            onBack = { navigator.goBack() },
            modifier = Modifier.padding(paddingValues),
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(tween(100)),
                    initialContentExit = fadeOut(tween(100))
                )
            }
        )
    }
}

