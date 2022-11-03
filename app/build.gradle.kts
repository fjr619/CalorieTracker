plugins {
    id ("com.android.application")
    id ("dagger.hilt.android.plugin")
}

apply {
    from("${rootProject.rootDir}/compose-module.gradle")
}

android {

    defaultConfig {
        applicationId = libs.versions.appId.get()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        release {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidX.coreKtx)
    implementation(libs.androidX.appCompat)
    implementation(libs.google.material)

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    implementation(projects.core)
    implementation(projects.coreUi)
    implementation(projects.onboarding.onboardingDomain)
    implementation(projects.onboarding.onboardingPresentation)
    implementation(projects.tracker.trackerData)
    implementation(projects.tracker.trackerDomain)
    implementation(projects.tracker.trackerPresentation)
}