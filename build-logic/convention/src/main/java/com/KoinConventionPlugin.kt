package com

import com.xsoft.convention.library
import com.xsoft.convention.libs
import com.xsoft.convention.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.withPlugin(libs.pluginId("kotlinMultiplatform")) {
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(project.dependencies.platform(libs.library("koin-bom")))
                        implementation(libs.library("koin-core"))
                        implementation(libs.library("koin-core-viewmodel"))
                        implementation(libs.library("koin-compose"))
                        implementation(libs.library("koin-compose-viewmodel"))
                    }

                    commonTest.dependencies {
                        implementation(libs.library("koin-test"))
                    }

                    androidMain.dependencies {
                        implementation(libs.library("koin-android"))
                    }
                }
            }
        }
    }
}