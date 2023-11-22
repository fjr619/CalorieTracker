package com.fjr619.onboarding.presentation.screen.age

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.UiText
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class AgeUiState(
    val selectAge: String = "20",
    val showSnackbar: StateEventWithContent<UiText> = consumed(),
    override val navigate: StateEventWithContent<UiEvent.Navigate> = consumed()
): OnboardingUiState {
}