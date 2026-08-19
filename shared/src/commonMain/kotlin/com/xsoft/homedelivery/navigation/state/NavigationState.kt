package com.xsoft.homedelivery.navigation.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import com.xsoft.designsystem.IconsHome.ic_home
import com.xsoft.designsystem.IconsHome.ic_record
import com.xsoft.designsystem.IconsHome.ic_support
import com.xsoft.history.navigation.History
import com.xsoft.history.navigation.HomeHistory
import com.xsoft.my_routes.navigation.HomeRoute
import com.xsoft.my_routes.navigation.MyRoutes
import com.xsoft.support.navigation.Support
import com.xsoft.support.navigation.SupportHomeScreen
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalSerializationApi::class)
private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class){
            subclassesOfSealed<MyRoutes>()
            subclassesOfSealed<History>()
            subclassesOfSealed<Support>()
        }
    }
}

class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute: NavKey by topLevelRoute
    val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }
}

data class NavBarItem(
    val icon: ImageVector,
    val title: String
)

val TOP_LEVEL_ROUTES = mapOf<NavKey, NavBarItem>(
    HomeRoute to NavBarItem(icon = ic_home, title ="Mis Rutas" ),
    HomeHistory(id = "") to NavBarItem(icon = ic_record, title = "Historial"),
    SupportHomeScreen to NavBarItem(icon = ic_support, title = "Soporte"),
)

@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationState {

    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        configuration = config,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ) {
        mutableStateOf(startRoute)
    }
    val backStacks = topLevelRoutes.associateWith { key ->
        rememberNavBackStack(config, key)
    }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}

@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorations = listOf(rememberSaveableStateHolderNavEntryDecorator<NavKey>())
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorations,
            entryProvider = entryProvider
        )
    }
    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}