plugins {
    alias(libs.plugins.ieu.android.feature)
    alias(libs.plugins.ieu.compose.multiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        namespace = "com.xsoft.login"
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


