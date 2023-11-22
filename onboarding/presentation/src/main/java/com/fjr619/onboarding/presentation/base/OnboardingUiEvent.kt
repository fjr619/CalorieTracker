package com.fjr619.onboarding.presentation.base

import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.ui.UiEvent

sealed class OnboardingUiEvent {

    data class NextPage(val uiEvent: UiEvent.Navigate): OnboardingUiEvent()
    data class SelectGender(val gender: Gender): OnboardingUiEvent()
    data class SelectAge(val age: String): OnboardingUiEvent()
    data class SelectHeight(val height: Int): OnboardingUiEvent()
    data class SelectWeight(val weight: Float): OnboardingUiEvent()
}