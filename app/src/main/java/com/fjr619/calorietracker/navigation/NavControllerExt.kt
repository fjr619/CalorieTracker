package com.fjr619.calorietracker.navigation

import androidx.navigation.NavController
import com.fjr619.core.ui.UIEvent

fun NavController.navigate(event: UIEvent.Navigate) {
    this.navigate(event.route)
}