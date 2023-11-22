package com.fjr619.onboarding.presentation.screen.activity_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import com.fjr619.core.base.domain.model.ActivityLevel
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigate
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.component.ActionButton
import com.fjr619.onboarding.presentation.component.SelectableButton

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
                        UiEvent.Navigate(
                            Route.GOAL_SCREEN
                        )
                    )
                )
            },
            onSelectedLevel = { level ->
                viewModel.onEvent(OnboardingUiEvent.SelectActivityLevel(level))
            }
        )
    }
}

@Composable
fun ActivityLevelScreen(
    level: ActivityLevel,
    onNextClick: () -> Unit,
    onSelectedLevel: (ActivityLevel) -> Unit
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
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(R.string.low),
                    isSelected = level is ActivityLevel.Low
                ) {
                    onSelectedLevel(ActivityLevel.Low)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.medium),
                    isSelected = level is ActivityLevel.Medium
                ) {
                    onSelectedLevel(ActivityLevel.Medium)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.high),
                    isSelected = level is ActivityLevel.High
                ) {
                    onSelectedLevel(ActivityLevel.High)
                }
            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}