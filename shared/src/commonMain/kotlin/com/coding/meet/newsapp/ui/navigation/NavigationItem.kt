package com.coding.meet.newsapp.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

data class NavigationItem(
    val icon: ImageVector,
    val title: StringResource,
    val route: Route
)