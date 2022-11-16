package com.fjr619.onboarding_presentation.weight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.core.R
import com.fjr619.core.domain.preferences.Preferences
import com.fjr619.core.navigation.Route
import com.fjr619.core.util.UiEvent
import com.fjr619.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    private val _weight = MutableStateFlow("80.0")
    val weight = _weight.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            _weight.update {
                weight
            }
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.value.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}