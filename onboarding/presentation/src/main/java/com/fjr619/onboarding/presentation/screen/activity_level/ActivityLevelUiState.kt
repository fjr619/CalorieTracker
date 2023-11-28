package com.fjr619.onboarding.presentation.screen.activity_level

import com.fjr619.core.base.domain.model.ActivityLevel
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class ActivityLevelUiState(
    val level: ActivityLevel = ActivityLevel.Medium,
    override val navigate: StateEventWithContent<String> = consumed()
): OnboardingUiState {
}