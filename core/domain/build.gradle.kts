plugins {
    alias(libs.plugins.ieu.kotlin.multiplatform)
    alias(libs.plugins.ieu.koin)
}

kotlin {
    android {
        namespace = "com.maypo.domain"
    }
    sourceSets{
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.data)
        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}