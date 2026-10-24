package com.coding.meet.newsapp.ui.headline

import com.coding.meet.newsapp.domain.model.Article

data class HeadlineState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategory: String = "",
)

sealed interface HeadlineEvent {
    data class SelectCategory(val category: String) : HeadlineEvent
    data object Retry : HeadlineEvent
    data class OpenArticle(val article: com.coding.meet.newsapp.domain.model.Article) : HeadlineEvent
}

sealed interface HeadlineEffect {
    data class NavigateToDetail(val articleId: String) : HeadlineEffect
}
