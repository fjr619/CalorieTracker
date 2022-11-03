apply {
    from("${rootProject.rootDir}/libs-module.gradle")
    from("${rootProject.rootDir}/compose-module.gradle")
}

dependencies {
    "implementation"(projects.core)
    "implementation"(projects.tracker.trackerDomain)
    "implementation"(libs.coil.compose)
}