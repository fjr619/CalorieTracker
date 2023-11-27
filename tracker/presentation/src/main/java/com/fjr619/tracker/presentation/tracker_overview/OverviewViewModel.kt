package com.fjr619.tracker.presentation.tracker_overview

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.core.ui.viewmodel.CoreViewModel
import com.fjr619.tracker.domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val preferences: IPreferences,
    private val trackerUseCases: TrackerUseCases
) : CoreViewModel<OverviewUiState>() {

    private var getFoodsForDateJob: Job? = null
    override fun createInitialState(): OverviewUiState = OverviewUiState()

    init {
        viewModelScope.launch {
            preferences.saveShowOnboarding(false)
        }
    }

    fun onConsumedNavigate() {
        setState {
            copy(navigate = consumed())
        }
    }

    fun onEvent(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    setState {
                        copy(navigate = triggered(UiEvent.Navigate(
                            route = Route.SEARCH_SCREEN
                            + "/${event.meal.mealType.name}"
                            + "/${uiState.value.date.dayOfMonth}"
                            + "/${uiState.value.date.monthValue}"
                            + "/${uiState.value.date.year}"
                        )))
                    }
                }
            }
            is OverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoods()
                }
            }
            is OverviewEvent.OnNextDayClick -> {
                setState {
                    copy(date = uiState.value.date.plusDays(1))
                }
                refreshFoods()
            }
            is OverviewEvent.OnPreviousDayClick -> {
                setState {
                    copy(date = uiState.value.date.minusDays(1))
                }
                refreshFoods()
            }
            is OverviewEvent.OnToggleMealClick -> {
                setState {
                    copy(meals = uiState.value.meals.map {
                        if (it.name == event.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    })
                }
            }
        }
    }

    private fun refreshFoods() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases.getFoodsForDate(uiState.value.date)
            .onEach {
                val nutrientsResult = trackerUseCases.calculateMealNutrients(it)
                setState {
                    copy(
                        totalCarbs = nutrientsResult.totalCarbs,
                        totalProtein = nutrientsResult.totalProtein,
                        totalFat = nutrientsResult.totalFat,
                        totalCalories = nutrientsResult.totalCalories,
                        carbsGoal = nutrientsResult.carbsGoal,
                        proteinGoal = nutrientsResult.proteinGoal,
                        fatGoal = nutrientsResult.fatGoal,
                        caloriesGoal = nutrientsResult.caloriesGoal,
                        trackedFoods = it,
                        meals = uiState.value.meals.map {
                            val nutrientsForMeal = nutrientsResult.mealNutrients[it.mealType]
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                            it.copy(
                                carbs = nutrientsForMeal.carbs,
                                protein = nutrientsForMeal.protein,
                                fat = nutrientsForMeal.fat,
                                calories = nutrientsResult.caloriesGoal
                            )
                        }

                    )
                }
            }.launchIn(viewModelScope)
    }
}