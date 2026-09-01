package com

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationFlavorsConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins.withId(""){
            extensions.configure<ApplicationExtension>{
                flavorDimensions += "environment"
                productFlavors {
                    create("dev") {
                        dimension = "environment"
                    }

                    create("qa") {
                        dimension = "environment"
                    }

                    create("prod") {
                        dimension = "environment"
                    }
                }
            }
        }

    }
}