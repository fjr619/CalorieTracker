package com.fjr619.calorietracker.presentation.main

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fjr619.calorietracker.presentation.onboarding.ActivityLevel
import com.fjr619.calorietracker.presentation.onboarding.Age
import com.fjr619.calorietracker.presentation.onboarding.Gender
import com.fjr619.calorietracker.presentation.onboarding.Goal
import com.fjr619.calorietracker.presentation.onboarding.Height
import com.fjr619.calorietracker.presentation.onboarding.Nutrient
import com.fjr619.calorietracker.presentation.onboarding.Weight
import com.fjr619.calorietracker.presentation.tracker.Search
import com.fjr619.calorietracker.presentation.tracker.TrackerOverview
import com.fjr619.calorietracker.ui.theme.CalorieTrackerTheme
import com.fjr619.core.ui.navigation.NavRoutes
import com.fjr619.core.ui.navigation.Route
import com.fjr619.core.ui.snackbar.CustomSnackbar
import com.fjr619.onboarding.presentation.screen.WelcomeScreen
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
                            route = NavRoutes.ONBOARDING_ROUTE.path,
                            startDestination = NavRoutes.WELCOME_SCREEN.path
                        ) {
                            composable(NavRoutes.WELCOME_SCREEN.path) {
                                WelcomeScreen(onNextClick = {
                                    navController.navigate(NavRoutes.GENDER_SCREEN.path)
                                })
                            }

                            Gender(navController)
                            Age(snackbarHost, navController)
                            Height(snackbarHost, navController)
                            Weight(snackbarHost, navController)
                            ActivityLevel(navController)
                            Goal(navController)
                            Nutrient(snackbarHost, navController)
                        }

                        TrackerOverview(navController)
                        Search(snackbarHost, navController)

                    }
                }
            }
        }
    }


}
