apply {
    from("${rootProject.rootDir}/libs-module.gradle")
}

dependencies {
    "implementation"(projects.core)
}