package com.fjr619.calorietracker.presentation.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.tracker.presentation.tracker_overview.OverviewEvent
import com.fjr619.tracker.presentation.tracker_overview.OverviewScreen
import com.fjr619.tracker.presentation.tracker_overview.OverviewViewModel

fun NavGraphBuilder.TrackerOverview(navController: NavController) {
    composable(Route.TRACKER_OVERVIEW_SCREEN) {
        val viewModel: OverviewViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = {
                navController.navigate(
                    it.first
                            + "/${it.second.mealType.name}"
                            + "/${state.date.dayOfMonth}"
                            + "/${state.date.monthValue}"
                            + "/${state.date.year}"
                )
            }
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            OverviewScreen(
                state = state,
                onPreviousDayClick = {
                    viewModel.onEvent(OverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(OverviewEvent.OnNextDayClick)
                },
                onToggleClick = {
                    viewModel.onEvent(OverviewEvent.OnToggleMealClick(it))
                },
                onDeletedClick = {
                    viewModel.onEvent(OverviewEvent.OnDeleteTrackedFoodClick(it))
                },
                onAddFoodcClick = {
                    viewModel.onEvent(OverviewEvent.OnAddFoodClick(it))
                }
            )
        }

    }
}