package com.coding.meet.newsapp.ui.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Standard Material Design 3 8dp-based spacing tokens.
 */
data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 24.dp,
    val extraExtraLarge: Dp = 32.dp,
    val huge: Dp = 48.dp,
) {
    val xs: Dp get() = extraSmall
    val sm: Dp get() = small
    val md: Dp get() = medium
    val lg: Dp get() = large
    val xl: Dp get() = extraLarge
    val xxl: Dp get() = extraExtraLarge
}

val LocalSpacing = staticCompositionLocalOf { Spacing() }

/**
 * Convenient accessor for design system spacing tokens via [MaterialTheme.spacing].
 */
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
