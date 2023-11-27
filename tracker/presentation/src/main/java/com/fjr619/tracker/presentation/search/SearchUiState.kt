package com.fjr619.tracker.presentation.search

import com.fjr619.core.base.util.UiText
import com.fjr619.core.ui.UiState
import com.fjr619.core.ui.compose_state_events.StateEvent
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.tracker.domain.model.TrackableFood

data class SearchUiState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList(),
    val showSnackbar: StateEventWithContent<UiText> = consumed(),
    val navigateUp: StateEvent = consumed
):UiState {
}


data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)