package com.fjr619.onboarding.presentation.screen.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fjr619.core.base.R
import com.fjr619.core.base.domain.model.GoalType
import com.fjr619.core.ui.LocalSpacing
import com.fjr619.onboarding.presentation.component.ActionButton
import com.fjr619.onboarding.presentation.component.SelectableButton

@Composable
fun GoalScreen(
    goalType: GoalType,
    onNextClick: () -> Unit,
    onSelectedType: (GoalType) -> Unit
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
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(R.string.lose),
                    isSelected = goalType is GoalType.LoseWeight
                ) {
                    onSelectedType(GoalType.LoseWeight)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.keep),
                    isSelected = goalType is GoalType.KeepWeight
                ) {
                    onSelectedType(GoalType.KeepWeight)
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.gain),
                    isSelected = goalType is GoalType.GainWeight
                ) {
                    onSelectedType(GoalType.GainWeight)
                }
            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}