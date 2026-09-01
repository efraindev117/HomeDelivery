    plugins {
        alias(libs.plugins.ieu.kotlin.multiplatform)
        alias(libs.plugins.ieu.koin)
    }

    kotlin {
        android {
            namespace = "com.maypo.common"
            withHostTest {}
        }
        sourceSets{
            commonMain.dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
            androidMain.dependencies {

            }
            iosMain.dependencies {

            }
        }
    }

