package com.fjr619.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fjr619.calorietracker.navigation.navigate
import com.fjr619.calorietracker.ui.theme.CalorieTrackerTheme
import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.UIEvent
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.onboarding.presentation.gender.GenderViewModel
import com.fjr619.onboarding.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = Route.WELCOME_SCREEN
                ) {
                    composable(Route.WELCOME_SCREEN) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }

                    composable(Route.AGE_SCREEN) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Text(text = Route.AGE_SCREEN)
                        }
                    }
                    composable(Route.GENDER_SCREEN) {
                        val genderViewModel = hiltViewModel<GenderViewModel>()
                        val uiState by genderViewModel.uiState.collectAsStateWithLifecycle()

                        EventEffect(
                            event = uiState.navigate,
                            onConsumed = genderViewModel::onConsumedNavigate,
                            action = {
                                navController.navigate(it)
                            })

                        Surface(modifier = Modifier.fillMaxSize()) {
                            Button(
                                modifier = Modifier.wrapContentSize(),
                                onClick = { genderViewModel.onEvent(UIEvent.Navigate(Route.AGE_SCREEN)) }
                            ){
                                Text(text = Route.GENDER_SCREEN)
                            }
                        }
                    }
                    composable(Route.HEIGHT) {

                    }
                    composable(Route.WEIGHT) {

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