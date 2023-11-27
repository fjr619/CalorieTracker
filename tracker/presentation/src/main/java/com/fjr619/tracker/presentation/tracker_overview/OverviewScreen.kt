package com.fjr619.tracker.presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.fjr619.core.base.R
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.tracker.domain.model.TrackedFood
import com.fjr619.tracker.presentation.tracker_overview.component.AddButton
import com.fjr619.tracker.presentation.tracker_overview.component.DaySelector
import com.fjr619.tracker.presentation.tracker_overview.component.ExpandableMeal
import com.fjr619.tracker.presentation.tracker_overview.component.NutrientsHeader
import com.fjr619.tracker.presentation.tracker_overview.component.TrackedFoodItem

@Composable
fun OverviewScreen(
    state: OverviewUiState,
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    onToggleClick: (Meal) -> Unit,
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
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

        items(state.meals) { meal ->
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
                        state.trackedFoods.forEach { food ->
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
                                onNavigateToSearch(
                                    meal.name.asString(context),
                                    state.date.dayOfMonth,
                                    state.date.monthValue,
                                    state.date.year
                                )
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