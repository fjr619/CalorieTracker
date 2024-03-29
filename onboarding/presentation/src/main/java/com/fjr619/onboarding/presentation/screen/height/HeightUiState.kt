package com.fjr619.onboarding.presentation.screen.height

import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class HeightUiState(
    val height: String = "180",
    override val showSnackbar: StateEventWithContent<UiText> = consumed(),
    override val navigate: StateEventWithContent<String> = consumed()
): OnboardingUiState{
}