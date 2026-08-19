package com

import com.xsoft.convention.library
import com.xsoft.convention.libs
import com.xsoft.convention.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginId("ieu.kotlin.multiplatform"))
        pluginManager.apply(libs.pluginId("ieu.compose.multiplatform"))

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.library("jetbrains-navigation3-ui"))
                    implementation(libs.library("kotlinxSerializationCore"))
                    implementation(project(":core:designsystem"))
                    //implementation(project(":core:ui"))
                }
            }
        }
    }
}