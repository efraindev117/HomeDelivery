plugins {
    alias(libs.plugins.ieu.android.feature)
    alias(libs.plugins.ieu.compose.multiplatform)
    alias(libs.plugins.kotlinSerialization)
}


kotlin {

    android {
        namespace = "com.maypo.login"
    }
    sourceSets {
        commonMain.dependencies {

        }
        androidMain.dependencies {
            implementation(libs.microsoft.msal)
        }
        iosMain.dependencies {

        }
    }
}