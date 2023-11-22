package com.fjr619.onboarding.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    color: Color = MaterialTheme.colorScheme.secondary,
    selectedTextColor: Color = Color.White,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    onClick: ()-> Unit
) {

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) color else Color.Transparent,
            contentColor = if (isSelected) selectedTextColor else color
        ),
        border = BorderStroke(1.dp, color)
    ) {
        Text(text = text, style = style)
    }
}

@Composable
@Preview
fun PreviewSelectableButton() {
    SelectableButton(
        text = "AAAa",
        isSelected = true,
        onClick = {}
    )
}