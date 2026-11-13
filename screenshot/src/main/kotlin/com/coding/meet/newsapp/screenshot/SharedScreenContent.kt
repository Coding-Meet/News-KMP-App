package com.coding.meet.newsapp.screenshot

import androidx.compose.runtime.Composable
import com.coding.meet.newsapp.domain.model.Article
import com.coding.meet.newsapp.ui.screen.article_detail.ArticleDetailScreenContent
import com.coding.meet.newsapp.ui.screen.article_detail.ArticleDetailState
import com.coding.meet.newsapp.ui.screen.bookmark.BookmarkScreenContent
import com.coding.meet.newsapp.ui.screen.bookmark.BookmarkState
import com.coding.meet.newsapp.ui.screen.headline.HeadlineScreenContent
import com.coding.meet.newsapp.ui.screen.headline.HeadlineState
import com.coding.meet.newsapp.ui.screen.search.SearchScreenContent
import com.coding.meet.newsapp.ui.screen.search.SearchState
import com.coding.meet.newsapp.ui.screen.setting.SettingScreenCotent
import com.coding.meet.newsapp.ui.screen.setting.SettingState
import com.coding.meet.newsapp.utils.Theme

// =============================================================================
// Common Screen Content (Shared across Mobile, Desktop & Web Screenshots)
// =============================================================================

@Composable
internal fun HeadlinesContent(
    selectedCategory: String = "Business",
    articles: List<Article> = sampleArticles,
) {
    HeadlineScreenContent(
        state = HeadlineState(
            articles = articles,
            selectedCategory = selectedCategory,
            isLoading = false,
        ),
        onEvent = {},
        onSettingsClick = {},
    )
}

@Composable
internal fun HeadlinesShimmerContent(
    selectedCategory: String = "Technology",
) {
    HeadlineScreenContent(
        state = HeadlineState(
            isLoading = true,
            selectedCategory = selectedCategory,
        ),
        onEvent = {},
        onSettingsClick = {},
    )
}

@Composable
internal fun ArticleDetailContent(
    article: Article = sampleSelectedArticle,
    isBookmarked: Boolean = true,
) {
    ArticleDetailScreenContent(
        state = ArticleDetailState(
            article = article,
            isLoading = false,
            isBookmarked = isBookmarked,
        ),
        onEvent = {},
    )
}

@Composable
internal fun SearchEmptyContent() {
    SearchScreenContent(
        state = SearchState(
            query = "",
            articles = emptyList(),
            isLoading = false,
            isIdle = true,
        ),
        onEvent = {},
    )
}

@Composable
internal fun SearchResultsContent(
    query: String = "AI",
    articles: List<Article> = sampleSearchArticles,
) {
    SearchScreenContent(
        state = SearchState(
            query = query,
            articles = articles,
            isLoading = false,
            isIdle = false,
        ),
        onEvent = {},
    )
}

@Composable
internal fun BookmarksEmptyContent() {
    BookmarkScreenContent(
        state = BookmarkState(
            articles = emptyList(),
            isLoading = false,
        ),
        onEvent = {},
    )
}

@Composable
internal fun BookmarksSavedContent(
    bookmarks: List<Article> = sampleBookmarkedArticles,
) {
    BookmarkScreenContent(
        state = BookmarkState(
            articles = bookmarks,
            isLoading = false,
        ),
        onEvent = {},
    )
}

@Composable
internal fun SettingsContent(
    isDark: Boolean = true,
) {
    SettingScreenCotent(
        state = SettingState(
            currentThemeName = if (isDark) Theme.DARK_MODE.name else Theme.LIGHT_MODE.name,
        ),
        onEvent = {},
    )
}

@Composable
internal fun ThemeDialogContent(
    isDark: Boolean = true,
) {
    SettingScreenCotent(
        state = SettingState(
            currentThemeName = if (isDark) Theme.DARK_MODE.name else Theme.LIGHT_MODE.name,
            showThemeDialog = true,
        ),
        onEvent = {},
    )
}

@Composable
internal fun ClearBookmarksDialogContent(
    isDark: Boolean = true,
) {
    SettingScreenCotent(
        state = SettingState(
            currentThemeName = if (isDark) Theme.DARK_MODE.name else Theme.LIGHT_MODE.name,
            showDeleteDialog = true,
        ),
        onEvent = {},
    )
}
