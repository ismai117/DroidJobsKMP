rootProject.name = "DroidJobsKMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
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
                maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":server")
include(":shared")