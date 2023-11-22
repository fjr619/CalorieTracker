package com.fjr619.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class CoreViewModel<State: UIState>: ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State
    abstract fun onConsumedNavigate()

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private var currentState: State
        get() = uiState.value
        set(value) {
            _uiState.update { value }
        }

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        currentState = currentState.reduce()
    }

}