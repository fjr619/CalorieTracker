package com.fjr619.onboarding.presentation.screen.nutrient

import com.fjr619.core.ui.UiEvent
import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class NutrientUiState(
    val carbRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30",
    override val showSnackbar: StateEventWithContent<UiText> = consumed(),
    override val navigate: StateEventWithContent<UiEvent.Navigate> = consumed()
): OnboardingUiState {
}