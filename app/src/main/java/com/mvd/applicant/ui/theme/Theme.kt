package com.mvd.applicant.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = MvdBlue,
    onPrimary = MvdWhite,
    secondary = MvdRed,
    onSecondary = MvdWhite,
    background = MvdWhite,
    onBackground = MvdTextPrimary,
    surface = MvdWhite,
    onSurface = MvdTextPrimary,
    surfaceVariant = MvdGray,
    outline = MvdDivider,
    error = MvdRed
)

@Composable
fun MvdApplicantTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
