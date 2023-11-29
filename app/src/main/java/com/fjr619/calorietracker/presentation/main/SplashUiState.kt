package com.fjr619.calorietracker.presentation.main

import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.UiState
import com.fjr619.core.ui.navigation.NavRoutes

data class SplashUiState(
    val isLoading: Boolean = true,
    val startDestination: String = NavRoutes.ONBOARDING_ROUTE.path
): UiState