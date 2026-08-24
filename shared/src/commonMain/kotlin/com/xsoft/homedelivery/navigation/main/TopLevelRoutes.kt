package com.xsoft.homedelivery.navigation.main

import com.xsoft.designsystem.IconsHome.ic_home
import com.xsoft.designsystem.IconsHome.ic_record
import com.xsoft.designsystem.IconsHome.ic_support
import com.xsoft.history.navigation.HomeHistory
import com.xsoft.my_routes.navigation.HomeRoute
import com.xsoft.support.navigation.SupportHomeScreen
import com.xsoft.ui.navigation.HomeDeliveryNavigationItem
import androidx.navigation3.runtime.NavKey

val TOP_LEVEL_ROUTES = mapOf<NavKey, HomeDeliveryNavigationItem>(
    HomeRoute to HomeDeliveryNavigationItem(
        icon = ic_home,
        title = "Mis Rutas"
    ),
    HomeHistory(id = "") to HomeDeliveryNavigationItem(
        icon = ic_record,
        title = "Historial"
    ),
    SupportHomeScreen to HomeDeliveryNavigationItem(
        icon = ic_support,
        title = "Soporte"
    )
)