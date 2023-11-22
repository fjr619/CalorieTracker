package com.fjr619.onboarding.presentation.screen.gender

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.core.ui.viewmodel.CoreViewModel
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.base.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: IPreferences
): OnboardingViewModel<GenderUiState>() {
    override fun createInitialState(): GenderUiState = GenderUiState()
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
                    copy(navigate = triggered(event.uiEvent))
                }
            }
            is OnboardingUiEvent.SelectGender -> {
                viewModelScope.launch {
                    preferences.saveGender(event.gender)
                    setState {
                        copy(gender = event.gender)
                    }
                }
            }
            else -> {}
        }
    }
}