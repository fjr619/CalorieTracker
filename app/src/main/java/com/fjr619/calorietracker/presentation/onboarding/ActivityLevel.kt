package com.fjr619.calorietracker.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.screen.activity_level.ActivityLevelScreen
import com.fjr619.onboarding.presentation.screen.activity_level.ActivityLevelViewModel

fun NavGraphBuilder.ActivityLevel(navController: NavController) {
    composable(Route.ACTIVITY_SCREEN) {
        val viewModel: ActivityLevelViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )

        ActivityLevelScreen(
            level = state.level,
            onNextClick = {
                viewModel.onEvent(
                    OnboardingUiEvent.NextPage(
                        Route.GOAL_SCREEN
                    )
                )
            },
            onSelectedLevel = { level ->
                viewModel.onEvent(OnboardingUiEvent.SelectActivityLevel(level))
            }
        )
    }
}