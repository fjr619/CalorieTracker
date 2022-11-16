package com.fjr619.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjr619.core.domain.preferences.Preferences
import com.fjr619.core.domain.use_case.FilterOutDigits
import com.fjr619.core.util.UiEvent
import com.fjr619.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.fjr619.core.R
import com.fjr619.core.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    //ini menggunakan flow, sebagai contoh lain yang bisa digunakan untuk simpan data
    //berbeda dengan di gender viewmodel, dia menggunakan MutableState
    //source : https://www.youtube.com/watch?v=T8vApYJlW8o
    private val _age = MutableStateFlow("20")
    val age = _age.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
            _age.update {
                filterOutDigits(age)
            }
//            _age.value = filterOutDigits(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.value.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }
}