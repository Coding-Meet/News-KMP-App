package com.coding.meet.newsapp.ui.article_detail

import com.coding.meet.newsapp.domain.model.Article

data class ArticleDetailState(
    val article: Article? = null,
    val isLoading: Boolean = true,
    val isBookmarked: Boolean = false,
    val error: String? = null,
)

sealed interface ArticleDetailEvent {
    data object ToggleBookmark : ArticleDetailEvent
    data object ShareArticle : ArticleDetailEvent
    data object OpenInBrowser : ArticleDetailEvent
    data object NavigateBack : ArticleDetailEvent
}

sealed interface ArticleDetailEffect {
    data class Share(val url: String) : ArticleDetailEffect
    data class OpenUrl(val url: String) : ArticleDetailEffect
    data object NavigateBack : ArticleDetailEffect
}
