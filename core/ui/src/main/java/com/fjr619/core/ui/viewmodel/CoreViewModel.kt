package com.fjr619.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.fjr619.core.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class CoreViewModel<State: UiState>: ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

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