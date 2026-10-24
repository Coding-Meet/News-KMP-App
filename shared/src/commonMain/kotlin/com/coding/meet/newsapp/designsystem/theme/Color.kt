package com.coding.meet.newsapp.designsystem.theme

import androidx.compose.ui.graphics.Color

// ── Light scheme ─────────────────────────────────────────────────────────────
val md_light_primary = Color(0xFF1565C0)           // deep news blue
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
val md_light_error = Color(0xFFD32F2F)
val md_light_onError = Color(0xFFFFFFFF)
val md_light_errorContainer = Color(0xFFFFDAD6)
val md_light_onErrorContainer = Color(0xFF410002)
val md_light_background = Color(0xFFF5F7FA)
val md_light_onBackground = Color(0xFF1A1C1E)
val md_light_surface = Color(0xFFFAFAFF)
val md_light_onSurface = Color(0xFF1A1C1E)
val md_light_surfaceVariant = Color(0xFFE1E5F0)
val md_light_onSurfaceVariant = Color(0xFF44474F)
val md_light_outline = Color(0xFF74777F)
val md_light_outlineVariant = Color(0xFFC4C7CF)

// ── Dark scheme ──────────────────────────────────────────────────────────────
val md_dark_primary = Color(0xFF90CAF9)            // sky blue
val md_dark_onPrimary = Color(0xFF003C8F)
val md_dark_primaryContainer = Color(0xFF0D47A1)
val md_dark_onPrimaryContainer = Color(0xFFD6E4FF)
val md_dark_secondary = Color(0xFF81D4FA)
val md_dark_onSecondary = Color(0xFF003352)
val md_dark_secondaryContainer = Color(0xFF004A75)
val md_dark_onSecondaryContainer = Color(0xFFCDE5FF)
val md_dark_tertiary = Color(0xFF5CD5FB)
val md_dark_onTertiary = Color(0xFF003547)
val md_dark_tertiaryContainer = Color(0xFF004D65)
val md_dark_onTertiaryContainer = Color(0xFFBBEAFF)
val md_dark_error = Color(0xFFEF9A9A)
val md_dark_onError = Color(0xFF690005)
val md_dark_errorContainer = Color(0xFF93000A)
val md_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_dark_background = Color(0xFF0F1114)
val md_dark_onBackground = Color(0xFFE2E2E6)
val md_dark_surface = Color(0xFF121318)
val md_dark_onSurface = Color(0xFFE2E2E6)
val md_dark_surfaceVariant = Color(0xFF44474F)
val md_dark_onSurfaceVariant = Color(0xFFC4C7CF)
val md_dark_outline = Color(0xFF8E9099)
val md_dark_outlineVariant = Color(0xFF44474F)

// ── Shimmer ───────────────────────────────────────────────────────────────────
val shimmerBase = Color(0xFFB0BEC5)

val shimmerColors = listOf(
    shimmerBase.copy(alpha = 0.3f),
    shimmerBase.copy(alpha = 0.5f),
    shimmerBase.copy(alpha = 1.0f),
    shimmerBase.copy(alpha = 0.5f),
    shimmerBase.copy(alpha = 0.3f),
)
