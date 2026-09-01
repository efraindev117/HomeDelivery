plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.koin)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        namespace = "com.maypo.core.network"
        androidResources {
            enable = true
        }
    }

    sourceSets{
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
        androidMain.dependencies {
            implementation(libs.microsoft.msal)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
