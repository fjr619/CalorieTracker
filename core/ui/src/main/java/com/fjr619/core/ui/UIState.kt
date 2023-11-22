package com.fjr619.core.ui

import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.compose_state_events.consumed

interface UIState {
    val navigate: StateEventWithContent<String>
        get() = consumed()
}