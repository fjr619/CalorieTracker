// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}