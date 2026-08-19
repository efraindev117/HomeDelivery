package com.xsoft.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configureAndroidKmpLibrary(
    namespace: String,
    compileSdk: Int,
    minSdk: Int,
    enableAndroidResources: Boolean
) {
    val androidTarget = (this as ExtensionAware).extensions
        .getByName("android") as KotlinMultiplatformAndroidLibraryTarget

    androidTarget.apply {
        this.namespace = namespace
        this.compileSdk = compileSdk
        this.minSdk = minSdk

        androidResources {
            enable = enableAndroidResources
        }

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}