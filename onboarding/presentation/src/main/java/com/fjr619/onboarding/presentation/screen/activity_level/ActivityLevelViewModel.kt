package com.fjr619.onboarding.presentation.screen.activity_level

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.base.OnboardingViewModel
import com.fjr619.onboarding.presentation.screen.gender.GenderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val preferences: IPreferences
): OnboardingViewModel<ActivityLevelUiState>() {
    override fun createInitialState(): ActivityLevelUiState = ActivityLevelUiState()
    override fun onConsumedNavigate() {
        setState {
            copy(navigate = consumed())
        }
    }

    override fun onConsumedSnackbar() {}

    fun onEvent(event: OnboardingUiEvent) {
        when(event) {
            is OnboardingUiEvent.NextPage -> {
                setState {
                    copy(navigate = triggered(event.route))
                }
            }
            is OnboardingUiEvent.SelectActivityLevel -> {
                viewModelScope.launch {
                    preferences.saveActivityLevel(event.level)
                    setState {
                        copy(level = event.level)
                    }
                }
            }
            else -> {}
        }
    }
}