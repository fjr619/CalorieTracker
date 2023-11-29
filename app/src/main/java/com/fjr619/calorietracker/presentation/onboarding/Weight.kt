package com.fjr619.calorietracker.presentation.onboarding

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigation.NavRoutes
import com.fjr619.core.ui.showSnackbar
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.screen.weight.WeightScreen
import com.fjr619.onboarding.presentation.screen.weight.WeightViewModel

fun NavGraphBuilder.Weight(snackbarHost: SnackbarHostState, navController: NavController) {
    composable(NavRoutes.WEIGHT_SCREEN.path) {
        val viewModel: WeightViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        showSnackbar(
            event = state.showSnackbar,
            onConsumed = viewModel::onConsumedSnackbar,
            snackbarHost = snackbarHost
        )

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )

        WeightScreen(
            weight = state.weight,
            onNextClick = {
                viewModel.onEvent(
                    OnboardingUiEvent.NextPage(NavRoutes.ACTIVITY_SCREEN.path)
                )
            },
            onSelectedHeight = {
                viewModel.onEvent(OnboardingUiEvent.SelectWeight(it))
            }
        )
    }
}