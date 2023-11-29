package com.fjr619.calorietracker.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigation.NavRoutes
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.screen.gender.GenderScreen
import com.fjr619.onboarding.presentation.screen.gender.GenderViewModel

fun NavGraphBuilder.Gender(navController: NavController) {
    composable(NavRoutes.GENDER_SCREEN.path) {
        val viewModel: GenderViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )

        GenderScreen(
            selectedGender = state.gender,
            onNextClick = {
                viewModel.onEvent(
                    OnboardingUiEvent.NextPage(NavRoutes.AGE_SCREEN.path)
                )
            },
            onSelectedGender = { gender ->
                viewModel.onEvent(OnboardingUiEvent.SelectGender(gender))
            }
        )
    }
}