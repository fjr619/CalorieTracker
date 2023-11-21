@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("plugin.app")
    id("plugin.app.compose")
    id("plugin.hilt")
    id("plugin.room")
//    alias(libs.plugins.com.android.application)
//    alias(libs.plugins.org.jetbrains.kotlin.android)
//    alias(libs.plugins.com.google.dagger.hilt.android)
//    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.fjr619.calorietracker"
//    compileSdk = 34

    defaultConfig {
        applicationId = "com.fjr619.calorietracker"
//        minSdk = 21
//        targetSdk = 34
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }

//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.3"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
}

dependencies {
    implementation(projects.onboarding.presentation)

//    implementation(libs.core.ktx)
//    implementation(libs.lifecycle.runtime.ktx)

//    implementation(libs.activity.compose)
//    implementation(platform(libs.compose.bom))
//    implementation(libs.ui)
//    implementation(libs.ui.graphics)
//    implementation(libs.ui.tooling.preview)
//    implementation(libs.material3)
//
//    androidTestImplementation(platform(libs.compose.bom))
//    androidTestImplementation(libs.ui.test.junit4)
//    debugImplementation(libs.ui.tooling)
//    debugImplementation(libs.ui.test.manifest)

    //coil
//    implementation(libs.coil)

//    //hilt
//    implementation(libs.hilt)
//    ksp (libs.hilt.compiler)
//    implementation(libs.hilt.compose)
//
//    //room
//    implementation(libs.room)
//    ksp (libs.room.compiler)
//    implementation(libs.room.ktx)
//
//    //retrofit


//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.espresso.core)

}