package com.coding.meet.newsapp.ui.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class NavigationItem(
    val icon: DrawableResource,
    val title: StringResource,
    val route: Route
)