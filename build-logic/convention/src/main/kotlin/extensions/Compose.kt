package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *,*>,
) {
    commonExtension.apply {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            libs.findVersion("kotlinCompilerExtensionVersion").get().toString()

        dependencies {
            "implementation"(libs.findLibrary("activity-compose").get())
            "implementation"(platform(libs.findLibrary("compose-bom").get()))
            "implementation"(libs.findBundle("compose").get())


            "androidTestImplementation"(platform(libs.findLibrary("compose-bom").get()))
            "testImplementation"(libs.findLibrary("ui-test-junit4").get())
            "debugImplementation"(libs.findLibrary("ui-tooling").get())
            "debugImplementation"(libs.findLibrary("ui-test-manifest").get()
            )
        }
    }
}