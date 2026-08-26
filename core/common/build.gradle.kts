    plugins {
        alias(libs.plugins.ieu.kotlin.multiplatform)
        alias(libs.plugins.ieu.koin)
    }

    kotlin {
        android {
            namespace = "com.maypo.common"
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

