package extensions

import com.android.build.api.dsl.CommonExtension
import config.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJunitRunner"
            vectorDrawables.useSupportLibrary = true
        }

        compileOptions {
            sourceCompatibility = Config.jvm.javaVersion
            targetCompatibility = Config.jvm.javaVersion
        }

        kotlinOptions {
            jvmTarget = Config.jvm.kotlinJvm
            freeCompilerArgs = freeCompilerArgs + Config.jvm.freeCompilerArgs
        }

        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

//        dependencies {
//            "implementation"(libs.findLibrary("core-ktx").get())
//            "implementation"(libs.findLibrary("lifecycle-runtime-ktx").get())
//            "testImplementation"(libs.findLibrary("junit").get())
//            "androidTestImplementation"(libs.findLibrary("androidx-test-ext-junit").get())
//            "androidTestImplementation"(libs.findLibrary("espresso-core").get())
//        }
    }
}