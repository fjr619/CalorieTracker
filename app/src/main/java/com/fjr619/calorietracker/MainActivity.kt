package com.fjr619.calorietracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fjr619.calorietracker.navigation.navigate
import com.fjr619.calorietracker.ui.theme.CalorieTrackerTheme
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.UiEvent
import com.fjr619.core.ui.UiText
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.snackbar.CustomSnackbar
import com.fjr619.core.ui.snackbar.CustomSnackbarVisual
import com.fjr619.onboarding.presentation.base.OnboardingUiEvent
import com.fjr619.onboarding.presentation.screen.WelcomeScreen
import com.fjr619.onboarding.presentation.screen.age.AgeScreen
import com.fjr619.onboarding.presentation.screen.age.AgeViewModel
import com.fjr619.onboarding.presentation.screen.gender.GenderScreen
import com.fjr619.onboarding.presentation.screen.gender.GenderViewModel
import com.fjr619.onboarding.presentation.screen.height.HeightScreen
import com.fjr619.onboarding.presentation.screen.height.HeightViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val snackbarHost = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHost) { data ->
                            CustomSnackbar(data)
                        }
                    }
                ) {
                    NavHost(
                        navController = navController, startDestination = Route.ONBOARDING_ROUTE
                    ) {
                        navigation(
                            route = Route.ONBOARDING_ROUTE,
                            startDestination = Route.WELCOME_SCREEN
                        ) {
                            composable(Route.WELCOME_SCREEN) {
                                WelcomeScreen(onNavigate = navController::navigate)
                            }

                            composable(Route.GENDER_SCREEN) {
                                val viewModel: GenderViewModel = hiltViewModel()
                                val state by viewModel.uiState.collectAsStateWithLifecycle()

                                EventEffect(
                                    event = state.navigate,
                                    onConsumed = viewModel::onConsumedNavigate,
                                    action = navController::navigate
                                )

                                GenderScreen(
                                    selectedGender = state.gender,
                                    onNextClick = {
                                        viewModel.onEvent(
                                            OnboardingUiEvent.NextPage(
                                                UiEvent.Navigate(
                                                    Route.AGE_SCREEN
                                                )
                                            )
                                        )
                                    },
                                    onSelectedGender = { gender ->
                                        viewModel.onEvent(OnboardingUiEvent.SelectGender(gender))
                                    }
                                )
                            }

                            composable(Route.AGE_SCREEN) {
                                val viewModel: AgeViewModel = hiltViewModel()
                                val state by viewModel.uiState.collectAsStateWithLifecycle()

                                showSnackbar(
                                    event = state.showSnackbar,
                                    onConsumed = viewModel::onConsumedSnackbar,
                                    snackbarHost = snackbarHost,
                                )

                                EventEffect(
                                    event = state.navigate,
                                    onConsumed = viewModel::onConsumedNavigate,
                                    action = navController::navigate
                                )


                                AgeScreen(
                                    age = state.age,
                                    onNextClick = {
                                        viewModel.onEvent(
                                            OnboardingUiEvent.NextPage(
                                                UiEvent.Navigate(
                                                    Route.HEIGHT_SCREEN
                                                )
                                            )
                                        )
                                    },
                                    onSelectedAge = {
                                        viewModel.onEvent(OnboardingUiEvent.SelectAge(it))
                                    }
                                )
                            }

                            composable(Route.HEIGHT_SCREEN) {
                                val viewModel: HeightViewModel = hiltViewModel()
                                val state by viewModel.uiState.collectAsStateWithLifecycle()

                                showSnackbar(
                                    event = state.showSnackbar,
                                    onConsumed = viewModel::onConsumedSnackbar,
                                    snackbarHost = snackbarHost
                                )

                                EventEffect(
                                    event = state.navigate,
                                    onConsumed = viewModel::onConsumedNavigate,
                                    action = navController::navigate
                                )

                                HeightScreen(
                                    height = state.height,
                                    onNextClick = {
                                        viewModel.onEvent(
                                            OnboardingUiEvent.NextPage(
                                                UiEvent.Navigate(
                                                    Route.WEIGHT_SCREEN
                                                )
                                            )
                                        )
                                    },
                                    onSelectedHeight = {
                                        viewModel.onEvent(OnboardingUiEvent.SelectHeight(it))
                                    }
                                )

                            }
                            composable(Route.WEIGHT_SCREEN) {

                            }
                        }

                        composable(Route.NUTRIENT_GOAL) {

                        }
                        composable(Route.ACTIVITY) {

                        }
                        composable(Route.GOAL) {

                        }

                        composable(Route.TRACKER_OVERVIEW) {

                        }
                        composable(Route.SEARCH) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun showSnackbar(
    isError: Boolean = true,
    event: StateEventWithContent<UiText>,
    onConsumed: () -> Unit,
    snackbarHost: SnackbarHostState
) {
    val context = LocalContext.current
    EventEffect(event = event,
        onConsumed = onConsumed,
        action = {
            snackbarHost.showSnackbar(
                visuals = CustomSnackbarVisual(
                    message = it.asString(context),
                    isError = isError,
                ),
            )
        })
}