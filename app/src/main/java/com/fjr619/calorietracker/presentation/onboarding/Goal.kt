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
import com.fjr619.onboarding.presentation.screen.goal.GoalScreen
import com.fjr619.onboarding.presentation.screen.goal.GoalViewModel

fun NavGraphBuilder.Goal(navController: NavController) {
    composable(NavRoutes.GOAL_SCREEN.path) {
        val viewModel: GoalViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )

        GoalScreen(
            goalType = state.goal,
            onNextClick = {
                viewModel.onEvent(
                    OnboardingUiEvent.NextPage(NavRoutes.NUTRIENT_GOAL_SCREEN.path)
                )
            },
            onSelectedType = { type ->
                viewModel.onEvent(OnboardingUiEvent.SelectGoalType(type))
            }
        )
    }
}