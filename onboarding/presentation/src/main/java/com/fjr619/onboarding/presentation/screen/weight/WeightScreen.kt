package com.fjr619.onboarding.presentation.screen.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.base.R
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigate
import com.fjr619.core.ui.showSnackbar
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.component.ActionButton
import com.fjr619.onboarding.presentation.component.UnitTextField
import com.fjr619.onboarding.presentation.screen.height.HeightScreen
import com.fjr619.onboarding.presentation.screen.height.HeightViewModel

fun NavGraphBuilder.Weight(snackbarHost: SnackbarHostState, navController: NavController) {
    composable(Route.WEIGHT_SCREEN) {
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
                    OnboardingUiEvent.NextPage(
                        UiEvent.Navigate(
                            Route.ACTIVITY
                        )
                    )
                )
            },
            onSelectedHeight = {
                viewModel.onEvent(OnboardingUiEvent.SelectWeight(it))
            }
        )
    }
}

@Composable
fun WeightScreen(
    weight: String,
    onNextClick: () -> Unit,
    onSelectedHeight: (String) -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = weight,
                onValueChange = onSelectedHeight,
                unit = stringResource(id = R.string.cm)
            )
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}