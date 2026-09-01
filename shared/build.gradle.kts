import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.compose.multiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ieu.koin)
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.kotlinxSerializationCore)
            //features
            implementation(projects.feature.auth)
            implementation(projects.feature.history)
            implementation(projects.feature.myRoutes)
            implementation(projects.feature.support)
            // core
            implementation(projects.core.common)
            implementation(projects.core.designsystem)
            implementation(projects.core.data)
            implementation(projects.core.domain)
        }

        androidMain.dependencies {
            // Android-only
        }

        iosMain.dependencies {
            // iOS-only
        }
    }
}
dependencies {

    androidRuntimeClasspath(libs.compose.uiTooling)
}

