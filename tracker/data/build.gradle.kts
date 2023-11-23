@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("plugin.lib")
    id("plugin.room")
    id("plugin.network")
}

android {
    namespace = "com.fjr619.onboarding.data"
}

dependencies {
    projects.core.apply {
        implementation(this.base)
    }
}