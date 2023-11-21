package com.fjr619.core.base.util

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    data object NavigateUp: UiEvent()
}
