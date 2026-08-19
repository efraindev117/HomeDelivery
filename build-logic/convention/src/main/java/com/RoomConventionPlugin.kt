package com

import androidx.room.gradle.RoomExtension
import com.xsoft.convention.library
import com.xsoft.convention.libs
import com.xsoft.convention.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class RoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginId("ksp"))
        pluginManager.apply(libs.pluginId("androidx-room"))

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(libs.library("androidx-room-runtime"))
                    implementation(libs.library("androidx-sqlite-bundled"))
                }
            }
        }

        extensions.configure<RoomExtension> {
            schemaDirectory("$projectDir/schemas")
        }

        dependencies {
            add("kspAndroid", libs.library("androidx-room-compiler"))
            add("kspIosArm64", libs.library("androidx-room-compiler"))
            add("kspIosSimulatorArm64", libs.library("androidx-room-compiler"))
        }
    }
}