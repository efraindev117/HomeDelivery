plugins {
    alias(libs.plugins.ieu.android.application)
    alias(libs.plugins.ieu.android.application.compose)
}

dependencies {
    implementation(projects.shared)
    implementation(libs.koin.android)
    implementation(platform(libs.koin.bom))
}