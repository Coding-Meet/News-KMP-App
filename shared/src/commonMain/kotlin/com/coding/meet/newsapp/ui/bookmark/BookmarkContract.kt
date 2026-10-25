package com.coding.meet.newsapp.ui.bookmark

import com.coding.meet.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface BookmarkEvent {
    data class OpenArticle(val article: Article) : BookmarkEvent
}

sealed interface BookmarkEffect {
    data class NavigateToDetail(val article: Article) : BookmarkEffect
}
