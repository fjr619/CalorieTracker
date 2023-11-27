package com.fjr619.tracker.presentation.tracker_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fjr619.core.base.R
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.navigate
import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.presentation.tracker_overview.component.AddButton
import com.fjr619.tracker.presentation.tracker_overview.component.DaySelector
import com.fjr619.tracker.presentation.tracker_overview.component.ExpandableMeal
import com.fjr619.tracker.presentation.tracker_overview.component.NutrientsHeader
import com.fjr619.tracker.presentation.tracker_overview.component.TrackedFoodItem

fun NavGraphBuilder.TrackerOverview(navController: NavController) {
    composable(Route.TRACKER_OVERVIEW_SCREEN) {
        val viewModel: OverviewViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        EventEffect(
            event = state.navigate,
            onConsumed = viewModel::onConsumedNavigate,
            action = navController::navigate
        )

        Surface(
            modifier = Modifier.fillMaxSize()
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

@Composable
fun OverviewScreen(
    state: OverviewUiState,
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    onToggleClick: (Meal) -> Unit,
    onAddFoodcClick: (Meal) -> Unit,
    onDeletedClick: (TrackedFood) -> Unit,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item(
            key = "NutrientsHeader"
        ) {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        item(
            key = "DaySelector"
        ) {
            DaySelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium),
                date = state.date,
                onPreviousDayClick = onPreviousDayClick,
                onNextDayClick = onNextDayClick
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }

        items(state.meals, key = {
            it.mealType.name
        }) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    onToggleClick(meal)
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
                        val foods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }

                        foods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = { onDeletedClick(food) })
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }

                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                onAddFoodcClick(meal)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}