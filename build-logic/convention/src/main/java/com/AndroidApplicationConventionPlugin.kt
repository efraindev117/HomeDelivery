package com

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.maypo.convention.AppEnvironment
import com.maypo.convention.appEnvironment
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

        val environment = appEnvironment()

        val buildId = providers
            .gradleProperty("BUILD_ID")
            .orNull
            ?.toIntOrNull()
            ?: 1

        extensions.configure<ApplicationExtension> {

            namespace = "com.maypo.homedelivery"
            compileSdk = libs.versionInt("android-compileSdk")

            defaultConfig {
                applicationId = "com.maypo.homedelivery"
                minSdk = libs.versionInt("android-minSdk")
                targetSdk = libs.versionInt("android-targetSdk")
                versionCode = buildId
                versionName = "1.0"
                testInstrumentationRunner =
                    "androidx.test.runner.AndroidJUnitRunner"
            }

            flavorDimensions += "environment"
            productFlavors {
                AppEnvironment.entries.forEach { environment ->
                    create(environment.flavorName) {
                        dimension = "environment"
                        environment.applicationIdSuffix?.let {
                            applicationIdSuffix = it
                        }
                        environment.versionNameSuffix?.let {
                            versionNameSuffix = it
                        }
                    }
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }

        extensions.configure<ApplicationAndroidComponentsExtension> {
            beforeVariants(selector().all()) { variantBuilder ->
                val variantEnvironment = variantBuilder.productFlavors
                    .firstOrNull { (dimension, _) ->
                        dimension == "environment"
                    }
                    ?.second

                if (
                    variantEnvironment != null &&
                    variantEnvironment != environment.flavorName
                ) {
                    variantBuilder.enable = false
                }
            }
        }

        // ...
    }
}