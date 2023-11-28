package com.fjr619.onboarding.presentation.base

import com.fjr619.core.base.domain.model.ActivityLevel
import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.base.domain.model.GoalType

sealed class OnboardingUiEvent {

    data class NextPage(val route: String): OnboardingUiEvent()
    data class SelectGender(val gender: Gender): OnboardingUiEvent()
    data class SelectAge(val age: String): OnboardingUiEvent()
    data class SelectHeight(val height: String): OnboardingUiEvent()
    data class SelectWeight(val weight: String): OnboardingUiEvent()
    data class SelectActivityLevel(val level: ActivityLevel): OnboardingUiEvent()
    data class SelectGoalType(val goalType: GoalType): OnboardingUiEvent()

    data class SelectCarbRatio(val ratio: String): OnboardingUiEvent()
    data class SelectProteinRatio(val ratio: String): OnboardingUiEvent()
    data class SelectFatRatio(val ratio: String): OnboardingUiEvent()
}