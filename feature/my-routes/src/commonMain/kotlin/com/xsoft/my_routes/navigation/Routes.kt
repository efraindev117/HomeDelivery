package com.xsoft.my_routes.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MyRoutes : NavKey

@Serializable
data object HomeRoute : MyRoutes

@Serializable
data object RouteDetail: MyRoutes


@Serializable
data object ReportIncident : MyRoutes
