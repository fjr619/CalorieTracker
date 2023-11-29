package com.fjr619.tracker.presentation.tracker_overview

import com.fjr619.tracker.domain.model.TrackedFood

sealed class OverviewEvent {
    object OnNextDayClick: OverviewEvent()
    object OnPreviousDayClick: OverviewEvent()
    data class OnToggleMealClick(val meal: Meal): OverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): OverviewEvent()
    data class GoToSearch(val route: String): OverviewEvent()
}