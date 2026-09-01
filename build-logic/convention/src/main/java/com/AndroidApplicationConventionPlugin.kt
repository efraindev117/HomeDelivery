package com

import com.android.build.api.dsl.ApplicationExtension
import com.maypo.convention.library
import com.maypo.convention.libs
import com.maypo.convention.pluginId
import com.maypo.convention.versionInt
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginId("androidApplication"))

        extensions.configure<ApplicationExtension> {
            namespace = "com.maypo.homedelivery"
            compileSdk = libs.versionInt("android-compileSdk")
            defaultConfig {
                applicationId = "com.maypo.homedelivery"
                minSdk = libs.versionInt("android-minSdk")
                targetSdk = libs.versionInt("android-targetSdk")
                versionCode = 1
                versionName = "1.0"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
                isCoreLibraryDesugaringEnabled = true
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }

        dependencies {
            add("coreLibraryDesugaring", libs.library("android-desugarJdkLibs"))
            add("testImplementation", libs.library("junit"))
            add("androidTestImplementation", libs.library("androidx-testExt-junit"))
            add("androidTestImplementation", libs.library("androidx-espresso-core"))
        }
    }
}