package com.fjr619.calorietracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

/**
 * M2	M3
 * h1	displayLarge
 * h2	displayMedium
 * h3	displaySmall
 * N/A	headlineLarge
 * h4	headlineMedium
 * h5	headlineSmall
 * h6	titleLarge
 * subtitle1	titleMedium
 * subtitle2	titleSmall
 * body1	bodyLarge
 * body2	bodyMedium
 * caption	bodySmall
 * button	labelLarge
 * N/A	labelMedium
 * overline	labelSmall
 */

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)