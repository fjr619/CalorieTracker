package com.fjr619.core.util

//defined what kind of event, that will like to send from ViewModel to composeable
//ex: navigate, backstack, snackbar, that which not state
sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
}
