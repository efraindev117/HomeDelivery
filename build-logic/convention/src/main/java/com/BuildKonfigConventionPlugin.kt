package com

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class BuildKonfigConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {

            pluginManager.apply("com.codingfeline.buildkonfig")

            val secrets = loadSecrets()

            val baseUrlDev = secrets.requiredProperty("BASE_URL_DEV")
            val baseUrlQa = secrets.requiredProperty("BASE_URL_QA")
            val baseUrlProd = secrets.requiredProperty("BASE_URL_PROD")

            extensions.configure<BuildKonfigExtension> {

                packageName.set("com.maypo.konfig")

                defaultConfigs {
                    buildConfigField(STRING, "environment", "prod")
                    buildConfigField(STRING, "baseUrl", baseUrlProd)
                    buildConfigField(BOOLEAN, "loggingEnabled", "false")
                }

                defaultConfigs("dev") {
                    buildConfigField(STRING, "environment", "dev")
                    buildConfigField(STRING, "baseUrl", baseUrlDev)
                    buildConfigField(BOOLEAN, "loggingEnabled", "true")
                }

                defaultConfigs("qa") {
                    buildConfigField(STRING, "environment", "qa")
                    buildConfigField(STRING, "baseUrl", baseUrlQa)
                    buildConfigField(BOOLEAN, "loggingEnabled", "true")
                }

                defaultConfigs("prod") {
                    buildConfigField(STRING, "environment", "prod")
                    buildConfigField(STRING, "baseUrl", baseUrlProd)
                    buildConfigField(BOOLEAN, "loggingEnabled", "false")
                }
            }
        }
    }

    private fun Project.loadSecrets(): Properties {
        val secretsFile = rootProject.file("secrets.properties")

        require(secretsFile.exists()) {
            "secrets.properties file was not found in project root."
        }

        return Properties().apply {
            secretsFile.inputStream().use(::load)
        }
    }

    private fun Properties.requiredProperty(key: String): String {
        return getProperty(key)
            ?.takeIf { it.isNotBlank() }
            ?: error("Missing required property '$key' in secrets.properties")
    }
}