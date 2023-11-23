@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("plugin.lib")
    id("plugin.hilt")
}

android {
    namespace = "com.fjr619.tracker.domain"
}

dependencies {
    implementation(projects.core.base)
}