package com.fjr619.onboarding.presentation.screen.weight

import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class WeightUiState(
    val weight: String = "80.0",
    override val showSnackbar: StateEventWithContent<UiText> = consumed(),
    override val navigate: StateEventWithContent<String> = consumed()
): OnboardingUiState {
}