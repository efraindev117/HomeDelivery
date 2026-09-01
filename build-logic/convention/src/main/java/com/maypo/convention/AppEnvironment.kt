package com.maypo.convention
internal enum class AppEnvironment(
    val flavorName: String,
    val applicationIdSuffix: String?,
    val versionNameSuffix: String?,
) {

    DEV(
        flavorName = "dev",
        applicationIdSuffix = ".dev",
        versionNameSuffix = "-dev",
    ),

    QA(
        flavorName = "qa",
        applicationIdSuffix = ".qa",
        versionNameSuffix = "-qa",
    ),

    PROD(
        flavorName = "prod",
        applicationIdSuffix = null,
        versionNameSuffix = null,
    ),
}