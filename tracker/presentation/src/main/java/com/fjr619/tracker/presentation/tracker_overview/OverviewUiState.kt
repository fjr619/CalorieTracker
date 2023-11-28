package com.fjr619.tracker.presentation.tracker_overview

import com.fjr619.core.ui.UiState
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.tracker.domain.model.TrackedFood
import java.time.LocalDate

data class OverviewUiState(
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val caloriesGoal: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val trackedFoods: List<TrackedFood> = emptyList(),
    val meals: List<Meal> = defaultMeals,
    val navigate: StateEventWithContent<Pair<String, Meal>> = consumed()
): UiState {
}