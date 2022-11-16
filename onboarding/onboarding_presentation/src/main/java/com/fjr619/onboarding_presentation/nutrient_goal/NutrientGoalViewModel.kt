package com.fjr619.onboarding_presentation.nutrient_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.core.domain.preferences.Preferences
import com.fjr619.core.domain.use_case.FilterOutDigits
import com.fjr619.core.navigation.Route
import com.fjr619.core.util.UiEvent
import com.fjr619.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrientsUseCase: ValidateNutrientsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<NutrientGoalState>(NutrientGoalState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent> ()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when(event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                _state.update {
                    it.copy(carbRatio = filterOutDigits(event.ratio))
                }
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                _state.update {
                    it.copy(proteinRatio = filterOutDigits(event.ratio))
                }
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                _state.update {
                    it.copy(fatRatio = filterOutDigits(event.ratio))
                }
            }
            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrientsUseCase(
                    carbsRatioText = state.value.carbRatio,
                    proteinRatioText = state.value.proteinRatio,
                    fatRatioText = state.value.fatRatio
                )

                when(result) {
                    is ValidateNutrientsUseCase.Result.Success -> {
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrientsUseCase.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }
        }
    }
}