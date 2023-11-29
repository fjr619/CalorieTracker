package com.fjr619.calorietracker.presentation.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.showSnackbar
import com.fjr619.tracker.domain.model.MealType
import com.fjr619.tracker.presentation.search.SearchEvent
import com.fjr619.tracker.presentation.search.SearchScreen
import com.fjr619.tracker.presentation.search.SearchViewModel
import java.time.LocalDate

fun NavGraphBuilder.Search(snackbarHost: SnackbarHostState, navController: NavController) {
    composable(
        Route.SEARCH_SCREEN + "/{mealName}/{dayOfMonth}/{month}/{year}",
        arguments = listOf(
            navArgument("mealName") {
                type = NavType.StringType
            },
            navArgument("dayOfMonth") {
                type = NavType.IntType
            },
            navArgument("month") {
                type = NavType.IntType
            },
            navArgument("year") {
                type = NavType.IntType
            },
        )
    ) {

        val mealName = it.arguments?.getString("mealName")!!
        val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
        val month = it.arguments?.getInt("month")!!
        val year = it.arguments?.getInt("year")!!

        val viewModel: SearchViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()


        showSnackbar(
            event = state.showSnackbar,
            onConsumed = viewModel::onConsumedSnackbar,
            snackbarHost = snackbarHost,
        )

        EventEffect(
            event = state.navigateUp,
            onConsumed = viewModel::onConsumedNavigateUp,
            action = {
                navController.navigateUp()
            }
        )

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            SearchScreen(
                mealName = mealName,
                state = state,
                onTextChange = {
                    viewModel.onEvent(SearchEvent.OnQueryChange(it))
                },
                onSearch = {
                    viewModel.onEvent(SearchEvent.OnSearch)
                },
                onItemClick = {
                    viewModel.onEvent(SearchEvent.OnToggleTrackableFood(it))
                },
                onAmountForFoodChange = { food, amount ->
                    viewModel.onEvent(
                        SearchEvent.OnAmountForFoodChange(
                            food,
                            amount
                        )
                    )
                },
                onTrack = {
                    viewModel.onEvent(
                        SearchEvent.OnTrackFoodClick(
                            food = it,
                            mealType = MealType.fromString(mealName),
                            date = LocalDate.of(year, month, dayOfMonth)
                        )
                    )
                }
            )
        }
    }
}