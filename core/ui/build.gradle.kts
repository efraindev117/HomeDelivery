plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.compose.multiplatform)
}

kotlin {
    android {
        namespace = "com.xsoft.ui"
    }
    sourceSets {
        commonMain.dependencies {

        }

        androidMain.dependencies {

        }

        iosMain.dependencies {

        }
    }
}