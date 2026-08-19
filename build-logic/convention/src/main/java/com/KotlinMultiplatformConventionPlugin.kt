package com

import com.xsoft.convention.configureAndroidKmpLibrary
import com.xsoft.convention.defaultKmpNamespace
import com.xsoft.convention.libs
import com.xsoft.convention.versionInt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("com.android.kotlin.multiplatform.library")

        extensions.configure<KotlinMultiplatformExtension> {
            configureAndroidKmpLibrary(
                namespace = defaultKmpNamespace(),
                compileSdk = libs.versionInt("android-compileSdk"),
                minSdk = libs.versionInt("android-minSdk"),
                enableAndroidResources = false
            )
            iosArm64()
            iosSimulatorArm64()

            sourceSets.apply {
                commonMain {
                    dependencies {
                        //  coroutines, datetime, ktor, etc.
                    }
                }

                commonTest {
                    dependencies {
                        implementation(kotlin("test"))
                    }
                }
            }
        }
    }
}
