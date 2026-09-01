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
        implementation(projects.core.common)
        implementation(projects.core.network)
        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}


