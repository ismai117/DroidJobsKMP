import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "webApp-wasm"
        browser {
            commonWebpackConfig {
                outputFileName = "webApp-wasm.js"
            }
        }
        binaries.executable()
        applyBinaryen()
    }

    sourceSets {
        val wasmJsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.ui)
                implementation("io.github.ismai117:kottie-wasm-js:1.7.3-alpha01")
            }
        }
    }
}

compose.experimental {
    web.application {}
}