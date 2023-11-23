package com.fjr619.onboarding.presentation.base

import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.UiState
import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed

//data class OnboardingUiState (
//    val gender: Gender = Gender.Male,
//    val age: String = "20",
//    val height: Int = 180,
//    val weight: Float = 80f
//    ): UiState

interface OnboardingUiState : UiState {
    val showSnackbar: StateEventWithContent<UiText>
        get() = consumed()

    val navigate: StateEventWithContent<UiEvent.Navigate>
}