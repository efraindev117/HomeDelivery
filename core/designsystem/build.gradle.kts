plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.compose.multiplatform)
}

kotlin {
    android {
        namespace = "com.xsoft.designsystem"
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.material.icons.extended)
        }

        androidMain.dependencies {

        }

        iosMain.dependencies {

        }
    }
}