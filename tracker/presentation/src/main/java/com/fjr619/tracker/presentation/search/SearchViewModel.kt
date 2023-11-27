package com.fjr619.tracker.presentation.search

import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.R
import com.fjr619.core.base.domain.use_case.FilterOutDigits
import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.core.ui.viewmodel.CoreViewModel
import com.fjr619.tracker.domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : CoreViewModel<SearchUiState>() {
    override fun createInitialState(): SearchUiState = SearchUiState()

    fun onConsumedSnackbar() {
        setState { copy(showSnackbar = consumed()) }
    }

    fun onConsumedNavigateUp() {
        setState {
            copy(navigateUp = consumed)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                setState {
                    copy(query = event.query)
                }
            }

            is SearchEvent.OnAmountForFoodChange -> {
                setState {
                    copy(
                        trackableFood = trackableFood.map {
                            if (it.food == event.food) {
                                it.copy(amount = filterOutDigits(event.amount))
                            } else it
                        }
                    )
                }
            }

            is SearchEvent.OnSearch -> {
                executeSearch()
            }

            is SearchEvent.OnToggleTrackableFood -> {
                setState {
                    copy(
                        trackableFood = trackableFood.map {
                            if (it.food == event.food) {
                                it.copy(isExpanded = !it.isExpanded)
                            } else it
                        }
                    )
                }
            }

            is SearchEvent.OnSearchFocusChange -> {
                setState {
                    copy(isHintVisible = !event.isFocused && query.isBlank())
                }
            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            setState {
                copy(
                    isSearching = true,
                    trackableFood = emptyList()
                )
            }
            trackerUseCases.searchFood(uiState.value.query)
                .onSuccess { foods ->
                    setState {
                        copy(
                            trackableFood = foods.map {
                                TrackableFoodUiState(it)
                            },
                            isSearching = false,
                            query = ""
                        )
                    }
                }
                .onFailure {
                    setState {
                        copy(
                            isSearching = false,
                            showSnackbar = triggered(UiText.StringResource(R.string.error_something_went_wrong))
                        )
                    }
                }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val trackableFoodState = uiState.value.trackableFood.find { it.food == event.food }
            trackerUseCases.trackFood(
                food = trackableFoodState?.food ?: return@launch,
                amount = trackableFoodState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )

            setState {
                copy(navigateUp = triggered)
            }
        }
    }
}