package com.coding.meet.newsapp.ui.designsystem.theme

import androidx.compose.ui.graphics.Color

// ── Light Scheme ─────────────────────────────────────────────────────────────
val md_light_primary = Color(0xFF1565C0)                // Deep news blue
val md_light_onPrimary = Color(0xFFFFFFFF)
val md_light_primaryContainer = Color(0xFFD6E4FF)
val md_light_onPrimaryContainer = Color(0xFF001946)

val md_light_secondary = Color(0xFF0277BD)
val md_light_onSecondary = Color(0xFFFFFFFF)
val md_light_secondaryContainer = Color(0xFFCDE5FF)
val md_light_onSecondaryContainer = Color(0xFF001D33)

val md_light_tertiary = Color(0xFF006685)
val md_light_onTertiary = Color(0xFFFFFFFF)
val md_light_tertiaryContainer = Color(0xFFBBEAFF)
val md_light_onTertiaryContainer = Color(0xFF001F2B)

val md_light_error = Color(0xFFBA1A1A)
val md_light_onError = Color(0xFFFFFFFF)
val md_light_errorContainer = Color(0xFFFFDAD6)
val md_light_onErrorContainer = Color(0xFF410002)

val md_light_background = Color(0xFFF8F9FE)
val md_light_onBackground = Color(0xFF191C20)

val md_light_surface = Color(0xFFF8F9FE)
val md_light_onSurface = Color(0xFF191C20)
val md_light_surfaceVariant = Color(0xFFDFE2EB)
val md_light_onSurfaceVariant = Color(0xFF43474E)

val md_light_surfaceDim = Color(0xFFD8DAE0)
val md_light_surfaceBright = Color(0xFFF8F9FE)
val md_light_surfaceContainerLowest = Color(0xFFFFFFFF)
val md_light_surfaceContainerLow = Color(0xFFF2F3F9)
val md_light_surfaceContainer = Color(0xFFECEEF4)
val md_light_surfaceContainerHigh = Color(0xFFE6E8EE)
val md_light_surfaceContainerHighest = Color(0xFFE1E2E8)

val md_light_inverseSurface = Color(0xFF2E3135)
val md_light_inverseOnSurface = Color(0xFFEFF0F7)
val md_light_inversePrimary = Color(0xFF90CAF9)

val md_light_outline = Color(0xFF73777F)
val md_light_outlineVariant = Color(0xFFC3C6CF)
val md_light_scrim = Color(0xFF000000)

// ── Dark Scheme ──────────────────────────────────────────────────────────────
val md_dark_primary = Color(0xFF90CAF9)                 // Soft sky blue
val md_dark_onPrimary = Color(0xFF003258)
val md_dark_primaryContainer = Color(0xFF00487D)
val md_dark_onPrimaryContainer = Color(0xFFD6E4FF)

val md_dark_secondary = Color(0xFF81D4FA)
val md_dark_onSecondary = Color(0xFF00344F)
val md_dark_secondaryContainer = Color(0xFF004C70)
val md_dark_onSecondaryContainer = Color(0xFFCDE5FF)

val md_dark_tertiary = Color(0xFF5CD5FB)
val md_dark_onTertiary = Color(0xFF003546)
val md_dark_tertiaryContainer = Color(0xFF004D64)
val md_dark_onTertiaryContainer = Color(0xFFBBEAFF)

val md_dark_error = Color(0xFFFFB4AB)
val md_dark_onError = Color(0xFF690005)
val md_dark_errorContainer = Color(0xFF93000A)
val md_dark_onErrorContainer = Color(0xFFFFDAD6)

val md_dark_background = Color(0xFF111418)
val md_dark_onBackground = Color(0xFFE1E2E8)

val md_dark_surface = Color(0xFF111418)
val md_dark_onSurface = Color(0xFFE1E2E8)
val md_dark_surfaceVariant = Color(0xFF43474E)
val md_dark_onSurfaceVariant = Color(0xFFC3C6CF)

val md_dark_surfaceDim = Color(0xFF111418)
val md_dark_surfaceBright = Color(0xFF37393E)
val md_dark_surfaceContainerLowest = Color(0xFF0C0E12)
val md_dark_surfaceContainerLow = Color(0xFF191C20)
val md_dark_surfaceContainer = Color(0xFF1D2024)
val md_dark_surfaceContainerHigh = Color(0xFF282A2F)
val md_dark_surfaceContainerHighest = Color(0xFF33353A)

val md_dark_inverseSurface = Color(0xFFE1E2E8)
val md_dark_inverseOnSurface = Color(0xFF2E3135)
val md_dark_inversePrimary = Color(0xFF1565C0)

val md_dark_outline = Color(0xFF8D9199)
val md_dark_outlineVariant = Color(0xFF43474E)
val md_dark_scrim = Color(0xFF000000)

// ── Shimmer Tokens ───────────────────────────────────────────────────────────
val shimmerBase = Color(0xFFB0BEC5)

val shimmerColors = listOf(
    shimmerBase.copy(alpha = 0.25f),
    shimmerBase.copy(alpha = 0.50f),
    shimmerBase.copy(alpha = 0.85f),
    shimmerBase.copy(alpha = 0.50f),
    shimmerBase.copy(alpha = 0.25f),
)
