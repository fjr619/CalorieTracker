package com.fjr619.onboarding_presentation.acitivtylevel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.core.domain.model.ActivityLevel
import com.fjr619.core.domain.preferences.Preferences
import com.fjr619.core.navigation.Route
import com.fjr619.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    private val _selectedActivityLevel = MutableStateFlow<ActivityLevel>(ActivityLevel.Medium)
    val selectedActivityLevel = _selectedActivityLevel.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityLevelSelect(activityLevel: ActivityLevel) {
        _selectedActivityLevel.update { activityLevel }
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel.value)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }
}