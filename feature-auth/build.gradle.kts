@file:Suppress("OPT_IN_USAGE")

import java.util.Properties


plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
}

kotlin {

    androidTarget()

    jvm()

    js(IR) {
        browser()
        binaries.executable()
    }

    wasmJs() {
        browser()
        binaries.executable()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()


    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.voyagerNavigation)
            implementation(libs.voyagerScreenModel)
            implementation(libs.voyagerTransitions)
            implementation(libs.bundles.ktor.common)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.napier)
            implementation(libs.settings)
            implementation(project(":commonUI"))
            implementation(project(":navigation"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-annotations-common"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.ktor.client.js)
        }
    }
}


android {
    namespace = "org.ncgroup.droidjobskmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

buildConfig {
    buildConfigField(
        type = "String",
        name = "appId",
        value = "\"${properties.getProperty("appId")}\""
    )
}