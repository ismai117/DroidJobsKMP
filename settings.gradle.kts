rootProject.name = "DroidJobsKMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven( "https://androidx.dev/storage/compose-compiler/repository/")
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
        maven( "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

include(":feature-jobs")
include(":feature-auth")
include(":feature-settings")
include(":feature-bookmark")
include(":shared")
include(":commonFeatures")
include(":androidApp")
include(":desktopApp")
include(":webApp")
//include(":webApp-wasm")

