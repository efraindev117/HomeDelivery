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
            //feature
            implementation(projects.feature.login)
            implementation(projects.feature.history)
            implementation(projects.feature.myRoutes)
            implementation(projects.feature.support)
            //core
            implementation(projects.core.designsystem)
            implementation(projects.core.ui)
        }

        androidMain.dependencies {
            // Android-only si necesitas algo aquí
        }

        iosMain.dependencies {
            // iOS-only si necesitas algo aquí
        }
    }
}
dependencies {

    androidRuntimeClasspath(libs.compose.uiTooling)
}

