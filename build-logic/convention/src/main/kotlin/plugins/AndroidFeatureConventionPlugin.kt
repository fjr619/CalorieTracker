package plugins

import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("plugin.lib")
            apply("plugin.lib.compose")
            apply("plugin.hilt")
        }
    }

    private fun Project.applyDependencies() {
        dependencies {
            "api"(libs.findLibrary("hilt-compose").get())
            "api"(libs.findLibrary("core-ktx").get())
            "api"(libs.findLibrary("lifecycle-runtime-ktx").get())
//            "api"(project(":core:base"))
            "api"(project(":core:ui"))
        }
    }
}