package com.fjr619.calorietracker.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.viewmodel.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferences: IPreferences
): CoreViewModel<SplashUiState>() {
    override fun createInitialState(): SplashUiState = SplashUiState()

    init {
        viewModelScope.launch {
            preferences.showOnboarding().collect { showONboarding ->
                Log.e("TAG", "onboarding $showONboarding")
                if (showONboarding) {
                    setState {
                        copy(startDestination = Route.ONBOARDING_ROUTE)
                    }
                } else {
                    setState {
                        copy(startDestination = Route.TRACKER_OVERVIEW_SCREEN)
                    }
                }

                Log.e("TAG", "start ${uiState.value.startDestination}")

                setState {
                    copy(isLoading = false)
                }
            }
        }
    }
}