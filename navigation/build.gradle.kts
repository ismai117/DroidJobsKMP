@file:Suppress("OPT_IN_USAGE")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
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
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(libs.voyagerNavigation)
            implementation(libs.voyagerScreenModel)
            implementation(libs.voyagerTransitions)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
        }
        iosMain.dependencies {
        }
    }
}


android {
    namespace = "org.ncgroup.droidjobskmp.navigation"
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
