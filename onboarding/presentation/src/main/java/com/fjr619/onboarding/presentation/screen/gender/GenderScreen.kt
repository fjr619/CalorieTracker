package com.fjr619.onboarding.presentation.screen.gender

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.fjr619.core.base.R
import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigate
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.component.ActionButton
import com.fjr619.onboarding.presentation.component.SelectableButton

fun NavGraphBuilder.Gender(navController: NavController) {
    composable(Route.GENDER_SCREEN) {
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
                    OnboardingUiEvent.NextPage(
                        UiEvent.Navigate(
                            Route.AGE_SCREEN
                        )
                    )
                )
            },
            onSelectedGender = { gender ->
                viewModel.onEvent(OnboardingUiEvent.SelectGender(gender))
            }
        )
    }
}

@Composable
fun GenderScreen(
    selectedGender: Gender,
    onNextClick: () -> Unit,
    onSelectedGender: (Gender) -> Unit
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(R.string.male),
                    isSelected = selectedGender is Gender.Male
                ) {
                    onSelectedGender(Gender.Male)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(R.string.female),
                    isSelected = selectedGender is Gender.Female
                ) {
                    onSelectedGender(Gender.Female)
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