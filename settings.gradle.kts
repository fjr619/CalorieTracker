pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "CalorieTracker"
include(":app")
include(":core:ui")
include(":core:base")
include(":onboarding:domain")
include(":onboarding:presentation")
include(":tracker:data")
include(":tracker:domain")
include(":tracker:presentation")
