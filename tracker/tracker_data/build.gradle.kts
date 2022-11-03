apply {
    from("${rootProject.rootDir}/libs-module.gradle")
}

dependencies {
    "implementation"(projects.core)
    "implementation"(projects.tracker.trackerDomain)

    "implementation"(libs.okhttp)
    "implementation"(libs.retrofit)
    "implementation"(libs.okHttpLoggingInterceptor)
    "implementation"(libs.retrofit.moshiConverter)

    "kapt"(libs.roomCompiler)
    "implementation"(libs.roomKtx)
    "implementation"(libs.roomRuntime)
}