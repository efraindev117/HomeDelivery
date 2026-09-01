plugins {
    alias(libs.plugins.ieu.android.feature)
    alias(libs.plugins.ieu.compose.multiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ieu.koin)
}

kotlin {

android {
    namespace = "com.maypo.support"
}
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.domain)
        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}