@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("plugin.feature")
}

android {
    namespace = "com.fjr619.onboarding.presentation"
}

dependencies {
    projects.onboarding.apply {
        api(domain)
    }
}