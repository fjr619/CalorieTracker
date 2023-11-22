package com.fjr619.onboarding.presentation.screen.gender

import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class GenderUiState(
    val gender: Gender = Gender.Male,
    override val navigate: StateEventWithContent<UiEvent.Navigate> = consumed()
): OnboardingUiState