package com.fjr619.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fjr619.core.R
import com.fjr619.core.navigation.Route
import com.fjr619.core.util.UiEvent
import com.fjr619.core_ui.Dimensions
import com.fjr619.core_ui.LocalSpacing
import com.fjr619.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1 
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                onNavigate(UiEvent.Navigate(Route.AGE))
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}