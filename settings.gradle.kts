rootProject.name = "HomeDelivery"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri(
                "https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1"
            )

            content {
                includeGroup("com.microsoft.device.display")
            }
        }
    }
}

include(":androidApp")
include(":shared")

include(":core:network")
include(":core:designsystem")
include(":core:ui")
include(":core:model")
include(":core:domain")
include(":core:database")
include(":core:common")

include(":feature:my-routes")
include(":feature:history")
include(":feature:support")
include(":feature:auth")

include(":core:data")
include(":core:konfig")
