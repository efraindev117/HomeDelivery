package com

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.xsoft.convention.library
import com.xsoft.convention.libs
import com.xsoft.convention.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginId("composeMultiplatform"))
        pluginManager.apply(libs.pluginId("composeCompiler"))
        pluginManager.withPlugin(libs.pluginId("kotlinMultiplatform")) {
            extensions.configure<KotlinMultiplatformExtension> {
                configureAndroidResourcesForCompose()
                sourceSets.named("commonMain").configure {
                    dependencies {
                        implementation(libs.library("compose-runtime"))
                        implementation(libs.library("compose-foundation"))
                        implementation(libs.library("compose-material3"))
                        implementation(libs.library("compose-ui"))
                        implementation(libs.library("compose-resources"))
                        implementation(libs.library("compose-uiToolingPreview"))
                        implementation(libs.library("androidx-lifecycle-viewmodelCompose"))
                        implementation(libs.library("androidx-lifecycle-runtimeCompose"))
                    }
                }

                sourceSets.named("androidMain").configure {
                    dependencies {
                        implementation(libs.library("compose-uiToolingPreview"))
                    }
                }
            }
            dependencies {
                add("androidRuntimeClasspath",
                    libs.library("compose-uiTooling"))
            }
        }
    }
}

private fun KotlinMultiplatformExtension.configureAndroidResourcesForCompose() {
    val androidTarget = (this as ExtensionAware).extensions
        .getByName("android") as KotlinMultiplatformAndroidLibraryTarget
    androidTarget.apply {
        androidResources {
            enable = true
        }

        withHostTest {
            isIncludeAndroidResources = true
        }
    }
}