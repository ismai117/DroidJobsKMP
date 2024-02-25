rootProject.name = "DroidJobsKMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

include(":feature-jobs")
include(":feature-auth")
include(":feature-settings")
include(":navigation")
include(":shared")
include(":commonUI")
include(":androidApp")
include(":desktopApp")
include(":webApp")
include(":webApp-wasm")

