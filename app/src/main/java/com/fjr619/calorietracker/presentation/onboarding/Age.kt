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
import com.fjr619.core.ui.showSnackbar
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.screen.age.AgeScreen
import com.fjr619.onboarding.presentation.screen.age.AgeViewModel

fun NavGraphBuilder.Age(snackbarHost: SnackbarHostState, navController: NavController) {
    composable(Route.AGE_SCREEN) {
        val viewModel: AgeViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        showSnackbar(
            event = state.showSnackbar,
            onConsumed = viewModel::onConsumedSnackbar,
            snackbarHost = snackbarHost,
        )

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )


        AgeScreen(
            age = state.age,
            onNextClick = {
                viewModel.onEvent(
                    OnboardingUiEvent.NextPage(Route.HEIGHT_SCREEN)
                )
            },
            onSelectedAge = {
                viewModel.onEvent(OnboardingUiEvent.SelectAge(it))
            }
        )
    }
}