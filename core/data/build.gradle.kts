plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.koin)
}

kotlin {
    android {
        namespace = "com.maypo.data"
    }
    sourceSets{
        commonMain.dependencies {

        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}


