package com.fjr619.calorietracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fjr619.calorietracker.ui.theme.CalorieTrackerTheme
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.navigate
import com.fjr619.core.ui.snackbar.CustomSnackbar
import com.fjr619.onboarding.presentation.screen.WelcomeScreen
import com.fjr619.onboarding.presentation.screen.activity_level.ActivityLevel
import com.fjr619.onboarding.presentation.screen.age.Age
import com.fjr619.onboarding.presentation.screen.gender.Gender
import com.fjr619.onboarding.presentation.screen.goal.Goal
import com.fjr619.onboarding.presentation.screen.height.Height
import com.fjr619.onboarding.presentation.screen.nutrient.Nutrient
import com.fjr619.onboarding.presentation.screen.weight.Weight
import com.fjr619.tracker.presentation.tracker_overview.OverviewEvent
import com.fjr619.tracker.presentation.tracker_overview.OverviewScreen
import com.fjr619.tracker.presentation.tracker_overview.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val splashViewModel by viewModels<SplashViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition(condition = {
                    splashViewModel.uiState.value.isLoading
                })
            }

        val barColor = Color.Transparent.toArgb()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                barColor, barColor,
            ),
            navigationBarStyle = SystemBarStyle.light(
                barColor, barColor,
            ),
        )

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)




        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val snackbarHost = remember {
                    SnackbarHostState()
                }

                val state by splashViewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHost) { data ->
                            CustomSnackbar(data)
                        }
                    }
                ) {
                    NavHost(
                        navController = navController, startDestination = state.startDestination

                    ) {
                        navigation(
                            route = Route.ONBOARDING_ROUTE,
                            startDestination = Route.WELCOME_SCREEN
                        ) {
                            composable(Route.WELCOME_SCREEN) {
                                WelcomeScreen(onNavigate = navController::navigate)
                            }

                            Gender(navController)
                            Age(snackbarHost, navController)
                            Height(snackbarHost, navController)
                            Weight(snackbarHost, navController)
                            ActivityLevel(navController)
                            Goal(navController)
                            Nutrient(snackbarHost, navController)
                        }

                        composable(Route.NUTRIENT_GOAL_SCREEN) {

                        }


                        composable(Route.TRACKER_OVERVIEW_SCREEN) {
                            val viewModel: OverviewViewModel = hiltViewModel()
                            val state by viewModel.uiState.collectAsStateWithLifecycle()

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
                                onNavigateToSearch = { mealName, day, month, year ->
                                }
                            )
                        }
                        composable(Route.SEARCH) {

                        }
                    }
                }
            }
        }
    }


}
