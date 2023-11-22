package com.fjr619.onboarding.presentation.base

import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.UiState
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed

//data class OnboardingUiState (
//    val gender: Gender = Gender.Male,
//    val age: String = "20",
//    val height: Int = 180,
//    val weight: Float = 80f
//    ): UiState

interface OnboardingUiState : UiState {
    val navigate: StateEventWithContent<UiEvent.Navigate>
        get() = consumed()
}