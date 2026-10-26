package com.coding.meet.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.bookmark
import news_kmp_app.shared.generated.resources.headlines
import news_kmp_app.shared.generated.resources.search
import org.jetbrains.compose.resources.StringResource

enum class NavigationItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: StringResource,
    val route: Route
) {
    Headlines(
        selectedIcon = Icons.Filled.Newspaper,
        unselectedIcon = Icons.Outlined.Newspaper,
        title = Res.string.headlines,
        route = Route.Headline,
    ),
    Search(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        title = Res.string.search,
        route = Route.Search,
    ),
    Bookmark(
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder,
        title = Res.string.bookmark,
        route = Route.Bookmark,
    ),
}