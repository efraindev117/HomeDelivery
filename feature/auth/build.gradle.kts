plugins {
    alias(libs.plugins.ieu.android.feature)
    alias(libs.plugins.ieu.compose.multiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ieu.koin)
}

kotlin {
    android {
        namespace = "com.maypo.auth"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.common)
        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}