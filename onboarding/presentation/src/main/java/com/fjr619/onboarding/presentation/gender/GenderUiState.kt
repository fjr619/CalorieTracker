package com.fjr619.onboarding.presentation.gender

import com.fjr619.core.base.domain.model.Gender
import com.fjr619.core.ui.UIState
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed


data class GenderUiState (
    val selectedGender: Gender = Gender.Male,
    override val navigate: StateEventWithContent<String> = consumed()
): UIState