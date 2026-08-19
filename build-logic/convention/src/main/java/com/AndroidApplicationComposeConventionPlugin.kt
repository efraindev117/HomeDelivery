package com

import com.xsoft.convention.library
import com.xsoft.convention.libs
import com.xsoft.convention.pluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.pluginId("composeMultiplatform"))
        pluginManager.apply(libs.pluginId("composeCompiler"))
        dependencies {
            add("implementation", libs.library("androidx-activity-compose"))
            add("implementation", libs.library("compose-uiToolingPreview"))
            add("debugImplementation", libs.library("compose-uiTooling"))
        }
    }
}