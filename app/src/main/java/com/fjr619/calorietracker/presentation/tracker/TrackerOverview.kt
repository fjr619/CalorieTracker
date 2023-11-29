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
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigation.NavRoutes
import com.fjr619.tracker.presentation.tracker_overview.OverviewEvent
import com.fjr619.tracker.presentation.tracker_overview.OverviewScreen
import com.fjr619.tracker.presentation.tracker_overview.OverviewViewModel

fun NavGraphBuilder.TrackerOverview(navController: NavController) {
    composable(NavRoutes.TRACKER_OVERVIEW_SCREEN.path) {
        val viewModel: OverviewViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
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
                onAddFoodcClick = { meal ->
                    viewModel.onEvent(OverviewEvent.GoToSearch(
                        NavRoutes.SEARCH_SCREEN.build(
                            mealType = meal.mealType.name,
                            dayOfMonth = state.date.dayOfMonth,
                            monthValue = state.date.monthValue,
                            year = state.date.year
                        )
                    ))
                }
            )
        }

    }
}