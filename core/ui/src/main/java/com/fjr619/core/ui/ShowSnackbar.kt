package com.fjr619.core.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.fjr619.core.ui.compose_state_events.EventEffect
import com.fjr619.core.ui.compose_state_events.StateEventWithContent
import com.fjr619.core.ui.snackbar.CustomSnackbarVisual

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