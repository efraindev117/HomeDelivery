plugins {
    alias(libs.plugins.ieu.android.application)
    alias(libs.plugins.ieu.android.application.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.core.common)
    implementation(libs.koin.android)
    implementation(platform(libs.koin.bom))
}