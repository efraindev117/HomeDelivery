package com.xsoft.homedelivery.navigation.main

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.xsoft.history.navigation.historySection
import com.xsoft.homedelivery.navigation.config.navigationConfig
import com.xsoft.homedelivery.navigation.navigator.Navigator
import com.xsoft.homedelivery.navigation.state.rememberNavigationState
import com.xsoft.homedelivery.navigation.state.toEntries
import com.xsoft.my_routes.navigation.HomeRoute
import com.xsoft.my_routes.navigation.myRoutesSection
import com.xsoft.support.navigation.supportSection
import com.xsoft.ui.navigation.HomeDeliveryBottomNavigation
@Composable
fun MainNavigation() {
    val navigationState = rememberNavigationState(
        startRoute = HomeRoute,
        topLevelRoutes = TOP_LEVEL_ROUTES.keys,
        configuration = navigationConfig
    )

    val navigator = remember(navigationState) {
        Navigator(navigationState)
    }

    val entryProvider = entryProvider {
        myRoutesSection()
        historySection()
        supportSection()
    }

    HomeDeliveryBottomNavigation(
        items = TOP_LEVEL_ROUTES,
        selectedItem = navigationState.topLevelRoute,
        onItemSelected = navigator::navigate
    ) { paddingValues ->
        NavDisplay(
            entries = navigationState.toEntries(entryProvider),
            onBack = navigator::goBack,
            modifier = Modifier.padding(paddingValues),
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = fadeIn(
                        animationSpec = tween(100)
                    ),
                    initialContentExit = fadeOut(
                        animationSpec = tween(100)
                    )
                )
            }
        )
    }
}

