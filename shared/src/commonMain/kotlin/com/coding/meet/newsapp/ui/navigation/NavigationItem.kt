package com.coding.meet.newsapp.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

data class NavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: StringResource,
    val route: Route
) {
    val icon: ImageVector get() = unselectedIcon

    constructor(icon: ImageVector, title: StringResource, route: Route) : this(
        selectedIcon = icon,
        unselectedIcon = icon,
        title = title,
        route = route
    )
}