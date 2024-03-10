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
    }

    wasmJs {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()


    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.android)
            implementation(libs.composeIcons.featherIcons)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation("media.kamel:kamel-image:0.9.3-wasm")
            implementation(libs.settings)
            implementation(libs.voyagerScreenModel)
            implementation(libs.bundles.ktor.common)
            implementation(libs.napier)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
            implementation(libs.composeIcons.featherIcons)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.composeIcons.featherIcons)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.ktor.client.js)
            implementation(libs.composeIcons.featherIcons)
        }
    }
}


android {
    namespace = "org.ncgroup.droidjobskmp.commonUI"
    compileSdk =  libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildConfigField(
        type = "String",
        name = "dataId",
        value = "\"${properties.getProperty("dataId")}\""
    )
    buildConfigField(
        type = "String",
        name = "apiKey",
        value = "\"${properties.getProperty("apiKey")}\""
    )
}