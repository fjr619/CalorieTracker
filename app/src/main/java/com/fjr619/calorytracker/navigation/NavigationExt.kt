package com.fjr619.calorytracker.navigation

import androidx.navigation.NavController
import com.fjr619.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}