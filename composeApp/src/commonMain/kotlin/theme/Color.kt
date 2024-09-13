package theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val shimmer = Color(0xFFC3C3C3)

val shimmerColors = listOf(
    shimmer.copy(alpha = 0.3f),
    shimmer.copy(alpha = 0.5f),
    shimmer.copy(alpha = 1.0f),
    shimmer.copy(alpha = 0.5f),
    shimmer.copy(alpha = 0.3f),
)