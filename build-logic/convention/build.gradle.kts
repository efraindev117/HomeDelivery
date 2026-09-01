import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

group = "com.maypo.buildlogic.convention"

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.androidx.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = libs.plugins.ieu.kotlin.multiplatform.get().pluginId
            implementationClass = "com.KotlinMultiplatformConventionPlugin"
        }

        register("composeMultiplatform") {
            id = libs.plugins.ieu.compose.multiplatform.get().pluginId
            implementationClass = "com.ComposeMultiplatformConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.ieu.android.application.asProvider().get().pluginId
            implementationClass = "com.AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = libs.plugins.ieu.android.application.compose.get().pluginId
            implementationClass = "com.AndroidApplicationComposeConventionPlugin"
        }

        register("koin") {
            id = libs.plugins.ieu.koin.get().pluginId
            implementationClass = "com.KoinConventionPlugin"
        }

        register("room") {
            id = libs.plugins.ieu.room.get().pluginId
            implementationClass = "com.RoomConventionPlugin"
        }

        register("feature") {
            id = libs.plugins.ieu.android.feature.get().pluginId
            implementationClass = "com.FeatureConventionPlugin"
        }
    }
}
