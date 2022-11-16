package com.fjr619.onboarding_presentation.height

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.core.R
import com.fjr619.core.domain.preferences.Preferences
import com.fjr619.core.domain.use_case.FilterOutDigits
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
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {

    private val _height = MutableStateFlow("180")
    val height = _height.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height: String) {
        if(height.length <= 3) {
            _height.update {
                filterOutDigits(height)
            }
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.value.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}