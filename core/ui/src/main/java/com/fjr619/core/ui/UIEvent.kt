package com.fjr619.core.ui

sealed class UIEvent {
    data class Navigate(val route: String): UIEvent()
    data object NavigateUp: UIEvent()
}