@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("plugin.lib")
    id("plugin.hilt")
    id("plugin.room")
    id("plugin.network")
}

android {
    namespace = "com.fjr619.onboarding.data"
}

dependencies {
    implementation(projects.core.base)
    implementation(projects.tracker.domain)
}