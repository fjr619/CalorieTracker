package com.fjr619.calorietracker.navigation

import androidx.navigation.NavController
import com.fjr619.core.base.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}