package com.fjr619.calorietracker.presentation.main

import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.UiState

data class SplashUiState(
    val isLoading: Boolean = true,
    val startDestination: String = Route.ONBOARDING_ROUTE
): UiState