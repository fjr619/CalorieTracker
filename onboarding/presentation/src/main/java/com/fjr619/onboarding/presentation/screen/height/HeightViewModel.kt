package com.fjr619.onboarding.presentation.screen.height

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.R
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.base.domain.use_case.FilterOutDigits
import com.fjr619.core.ui.UiText
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.base.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: IPreferences,
    private val filterOutDigits: FilterOutDigits
): OnboardingViewModel<HeightUiState>(){

    override fun createInitialState(): HeightUiState = HeightUiState()

    override fun onConsumedNavigate() {
        setState { copy(navigate = consumed()) }
    }

    override fun onConsumedSnackbar() {
        setState { copy(showSnackbar = consumed()) }
    }

    fun onEvent(event: OnboardingUiEvent) {
        when (event) {
            is OnboardingUiEvent.NextPage -> {
                viewModelScope.launch {
                    val heightNumber  = uiState.value.height.toIntOrNull() ?: kotlin.run {
                        setState { copy(showSnackbar = triggered(UiText.StringResource(R.string.error_height_cant_be_empty))) }
                        return@launch
                    }

                    preferences.saveAge(heightNumber)
                    setState {
                        copy(navigate = triggered(event.uiEvent))
                    }
                }
            }

            is OnboardingUiEvent.SelectHeight -> {
                if (event.height.length <= 3)
                    setState { copy(height = filterOutDigits(event.height)) }
            }

            else -> {}
        }
    }
}