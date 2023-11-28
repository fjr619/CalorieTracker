package com.fjr619.onboarding.presentation.screen.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fjr619.core.base.R
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.onboarding.presentation.component.ActionButton
import com.fjr619.onboarding.presentation.component.UnitTextField

@Composable
fun WeightScreen(
    weight: String,
    onNextClick: () -> Unit,
    onSelectedHeight: (String) -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = weight,
                onValueChange = onSelectedHeight,
                unit = stringResource(id = R.string.cm)
            )
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}