plugins {
    `kotlin-dsl`
}

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = "plugin.app"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidAppCompose") {
            id = "plugin.app.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}