plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.koin)
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
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
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
