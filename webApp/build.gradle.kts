plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}


val copyJsResources = tasks.create("copyJsResourcesWorkaround", Copy::class.java) {
    from(project(":commonFeatures").file("src/commonMain/composeResources"))
    into("build/processedResources/js/main")
}

afterEvaluate {
    project.tasks.getByName("jsProcessResources").finalizedBy(copyJsResources)
    project.tasks.getByName("jsDevelopmentExecutableCompileSync").mustRunAfter(copyJsResources)
    project.tasks.getByName("jsProductionExecutableCompileSync").mustRunAfter(copyJsResources)
}


kotlin {
    js(IR) {
        moduleName = "webApp"
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js"
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                implementation(compose.ui)
                implementation(compose.html.core)
                implementation(compose.components.resources)
                implementation(project(":shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}
