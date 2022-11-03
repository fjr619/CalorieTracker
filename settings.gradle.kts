pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")

    }

//    versionCatalogs {
//        create("libs") {
//            from(files("gradle/libs.versions.toml"))
//        }
//    }
}

rootProject.name = "CaloryTracker"
include (":app")
include (":core")
include (":onboarding:onboarding_presentation")
include (":onboarding:onboarding_domain")
include (":tracker:tracker_data")
include (":tracker:tracker_domain")
include (":tracker:tracker_presentation")
