plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.home.delivery.build.konfig.plugin)
}

kotlin {
    android {
        namespace = "com.maypo.konfig"
    }

    sourceSets {
        commonMain.dependencies {
        }
    }
}

