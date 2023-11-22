@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("plugin.lib")
    id("plugin.hilt")
}

android {
    namespace = "com.fjr619.onboarding.domain"
}

dependencies {
//    implementation(projects.core.base)
    projects.core.apply {
        implementation(this.base)
    }
}
