package com.maypo.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions
        .getByType<VersionCatalogsExtension>()
        .named("libs")

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

internal fun VersionCatalog.versionInt(alias: String): Int =
    findVersion(alias)
        .get()
        .requiredVersion
        .toInt()

internal fun VersionCatalog.versionString(alias: String): String =
    findVersion(alias)
        .get()
        .requiredVersion

fun Project.defaultKmpNamespace(): String {
    val modulePath = path
        .removePrefix(":")
        .replace("-", ".")
        .replace(":", ".")
    return "com.maypo.homedelivery.$modulePath"
}

internal fun VersionCatalog.pluginId(alias: String): String =
    findPlugin(alias)
        .get()
        .get()
        .pluginId

internal fun VersionCatalog.library(alias: String) =
    findLibrary(alias)
        .get()

internal fun Project.appEnvironment(): AppEnvironment {

    val environment = providers
        .gradleProperty("buildkonfig.flavor")
        .getOrElse("prod")

    return AppEnvironment.entries.firstOrNull {
        it.flavorName == environment
    } ?: error(
        "Invalid buildkonfig.flavor='$environment'. " +
                "Valid values: dev, qa, prod."
    )
}

