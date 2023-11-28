package com.fjr619.onboarding.presentation.screen.goal

import com.fjr619.core.base.domain.model.GoalType
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.onboarding.presentation.base.OnboardingUiState

data class GoalUiState(
    val goal: GoalType = GoalType.KeepWeight,
    override val navigate: StateEventWithContent<String> = consumed()
): OnboardingUiState