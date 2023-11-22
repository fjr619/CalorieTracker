package com.fjr619.core.ui

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data object NavigateUp: UiEvent()
}
