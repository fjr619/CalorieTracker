package com.fjr619.onboarding.presentation.base

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fjr619.core.base.domain.preferences.IPreferences
import com.fjr619.core.base.domain.use_case.FilterOutDigits
import com.fjr619.core.ui.UiState
import com.fjr619.core.ui.viewmodel.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class  OnboardingViewModel<OnboardingUiState : UiState> : CoreViewModel<OnboardingUiState>() {
    abstract fun onConsumedNavigate()
}