package ui.bookmark

import data.model.Article

data class BookmarkUiState(
    val articles: List<Article> = emptyList()
)