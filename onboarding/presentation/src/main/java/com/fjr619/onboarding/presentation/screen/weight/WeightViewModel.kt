package com.fjr619.onboarding.presentation.screen.weight

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.R
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.base.domain.use_case.FilterOutDigits
import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.base.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: IPreferences,
    private val filterOutDigits: FilterOutDigits
): OnboardingViewModel<WeightUiState>(){

    override fun createInitialState(): WeightUiState = WeightUiState()

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
                    val weightNumber  = uiState.value.weight.toFloatOrNull() ?: kotlin.run {
                        setState { copy(showSnackbar = triggered(UiText.StringResource(R.string.error_height_cant_be_empty))) }
                        return@launch
                    }

                    preferences.saveWeight(weightNumber)
                    setState {
                        copy(navigate = triggered(event.uiEvent))
                    }
                }
            }

            is OnboardingUiEvent.SelectWeight -> {
                if (event.weight.length <= 5)
                    setState { copy(weight = filterOutDigits(event.weight)) }
            }

            else -> {}
        }
    }
}