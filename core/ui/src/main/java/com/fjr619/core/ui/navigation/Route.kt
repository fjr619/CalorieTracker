package com.fjr619.core.ui.navigation

sealed class NavRoutes(val path: String) {
    data object ONBOARDING_ROUTE : NavRoutes("onboardingRoute")
    data object WELCOME_SCREEN : NavRoutes("welcomeScreen")
    data object GENDER_SCREEN : NavRoutes("genderScreen")
    data object AGE_SCREEN : NavRoutes("ageScreen")
    data object HEIGHT_SCREEN : NavRoutes("heightScreen")
    data object WEIGHT_SCREEN : NavRoutes("weightScreen")
    data object ACTIVITY_SCREEN : NavRoutes("activityScreen")
    data object GOAL_SCREEN : NavRoutes("goalScreen")
    data object NUTRIENT_GOAL_SCREEN : NavRoutes("nutrientGoalScreen")
    data object TRACKER_OVERVIEW_SCREEN : NavRoutes("trackerOverviewScreen")

    data object SEARCH_SCREEN :
        NavRoutes("searchScreen/{$MEAL_NAME}/{$DAY_OF_MONTH}/{$MONTH_VALUE}/{$YEAR}") {
            fun build(mealType: String, dayOfMonth: Int, monthValue: Int, year: Int): String {
                return path.replace(
                    "{$MEAL_NAME}", mealType
                ).replace(
                    "{$DAY_OF_MONTH}", "$dayOfMonth"
                ).replace(
                 "{$MONTH_VALUE}", "$monthValue"
                ).replace(
                    "{$YEAR}", "$year"
                )
            }
        }

    companion object {
        const val MEAL_NAME = "mealName"
        const val DAY_OF_MONTH = "dayOfMonth"
        const val MONTH_VALUE = "monthValue"
        const val YEAR = "year"
    }
}

object Route {
    const val ONBOARDING_ROUTE = "onboardingRoute"
    const val WELCOME_SCREEN = "welcomeScreen"

    const val GENDER_SCREEN = "genderScreen"
    const val AGE_SCREEN = "ageScreen"
    const val HEIGHT_SCREEN = "heightScreen"
    const val WEIGHT_SCREEN = "weightScreen"
    const val ACTIVITY_SCREEN = "activityScreen"
    const val GOAL_SCREEN = "goalScreen"

    const val NUTRIENT_GOAL_SCREEN = "nutrientGoalScreen"

    const val TRACKER_OVERVIEW_SCREEN = "trackerOverviewScreen"
    const val SEARCH_SCREEN = "searchScreen"
}