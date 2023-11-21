package plugins

import extensions.applicationGradle
import extensions.configureBuildTypes
import extensions.configureKotlinAndroid
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            applyPlugins()

            applicationGradle {
                configureKotlinAndroid(this)
                defaultConfig {
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                }
                configureBuildTypes(this)
            }
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }
    }
}