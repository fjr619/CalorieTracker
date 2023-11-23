package com.fjr619.tracker.presentation.tracker_overview

import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.ui.viewmodel.CoreViewModel
import com.fjr619.tracker.domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val preferences: IPreferences,
    private val trackerUseCases: TrackerUseCases
): CoreViewModel<OverviewUiState>() {
    override fun createInitialState(): OverviewUiState = OverviewUiState()

    fun onEvent(event: OverviewEvent) {
        when(event) {
            is OverviewEvent.OnAddFoodClick -> {}
            is OverviewEvent.OnDeleteTrackedFoodClick -> {}
            is OverviewEvent.OnNextDayClick -> {}
            is OverviewEvent.OnPreviousDayClick -> {}
            is OverviewEvent.OnToggleMealClick -> {}
        }

    }
}