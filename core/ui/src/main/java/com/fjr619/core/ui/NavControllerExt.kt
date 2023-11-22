package com.fjr619.core.ui

import androidx.navigation.NavController
import com.fjr619.core.ui.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}