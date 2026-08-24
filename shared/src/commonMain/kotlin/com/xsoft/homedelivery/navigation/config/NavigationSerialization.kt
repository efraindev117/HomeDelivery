package com.xsoft.homedelivery.navigation.config

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.xsoft.history.navigation.History
import com.xsoft.login.navigation.Authentication
import com.xsoft.my_routes.navigation.MyRoutes
import com.xsoft.support.navigation.Support
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalSerializationApi::class)
val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclassesOfSealed<MyRoutes>()
            subclassesOfSealed<History>()
            subclassesOfSealed<Support>()
            subclassesOfSealed<Authentication>()
        }
    }
}