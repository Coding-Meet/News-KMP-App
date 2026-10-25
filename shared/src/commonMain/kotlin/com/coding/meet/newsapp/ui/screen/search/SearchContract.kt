package com.coding.meet.newsapp.ui.screen.search

import com.coding.meet.newsapp.domain.model.Article

data class SearchState(
    val query: String = "",
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isIdle: Boolean = true,
)

sealed interface SearchEvent {
    data class QueryChanged(val query: String) : SearchEvent
    data object Search : SearchEvent
    data object Retry : SearchEvent
    data class OpenArticle(val article: Article) : SearchEvent
}

sealed interface SearchEffect {
    data class NavigateToDetail(val article: Article) : SearchEffect
}
