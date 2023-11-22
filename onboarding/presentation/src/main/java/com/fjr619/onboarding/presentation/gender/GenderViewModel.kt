package com.fjr619.onboarding.presentation.gender

import com.fjr619.core.base.navigation.Route
import com.fjr619.core.ui.CoreViewModel
import com.fjr619.core.ui.UIEvent
import com.fjr619.core.ui.compose_state_events.consumed
import com.fjr619.core.ui.compose_state_events.triggered
import com.fjr619.onboarding.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase
): CoreViewModel<GenderUiState>() {
    override fun createInitialState(): GenderUiState = GenderUiState()
    override fun onConsumedNavigate() {
        setState {
            copy(
                navigate = consumed()
            )
        }
    }
    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.Navigate -> {
                setState {
                    copy(
                        navigate = triggered(Route.AGE_SCREEN)
                    )
                }
            }
            else -> {}
        }
    }
}