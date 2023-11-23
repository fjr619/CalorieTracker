package com.fjr619.calorietracker

import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.UiState

data class SplashUiState(
    val isLoading: Boolean = true,
    val startDestination: String = Route.ONBOARDING_ROUTE
): UiState