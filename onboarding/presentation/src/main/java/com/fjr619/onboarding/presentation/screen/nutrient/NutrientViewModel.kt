package com.fjr619.onboarding.presentation.screen.nutrient

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.base.domain.use_case.FilterOutDigits
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.onboarding.domain.usecase.ValidateNutrients
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.base.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientViewModel @Inject constructor(
    private val preferences: IPreferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): OnboardingViewModel<NutrientUiState>() {

    override fun createInitialState(): NutrientUiState = NutrientUiState()

    override fun onConsumedNavigate() {
        setState { copy(navigate = consumed()) }
    }

    override fun onConsumedSnackbar() {
        setState { copy(showSnackbar = consumed()) }
    }

    fun onEvent(event: OnboardingUiEvent) {
        when(event) {
            is OnboardingUiEvent.SelectCarbRatio -> {
                setState {
                    copy(carbRatio = filterOutDigits(event.ratio))
                }
            }
            is OnboardingUiEvent.SelectProteinRatio -> {
                setState {
                    copy(proteinRatio = filterOutDigits(event.ratio))
                }
            }
            is OnboardingUiEvent.SelectFatRatio -> {
                setState {
                    copy(fatRatio = filterOutDigits(event.ratio))
                }
            }
            is OnboardingUiEvent.NextPage -> {
                val result = validateNutrients(
                    carbsRatioText = uiState.value.carbRatio,
                    proteinRatioText = uiState.value.proteinRatio,
                    fatRatioText = uiState.value.fatRatio
                )

                when(result) {
                    is ValidateNutrients.Result.Error -> {
                        setState {
                            copy(showSnackbar = triggered(result.message))
                        }
                    }
                    is ValidateNutrients.Result.Success -> {
                        viewModelScope.launch {
                            preferences.saveProteinRatio(result.proteinRatio)
                            preferences.saveCarbRatio(result.carbsRatio)
                            preferences.saveFatRatio(result.fatRatio)
                            setState {
                                copy(navigate = triggered(event.uiEvent))
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }

}