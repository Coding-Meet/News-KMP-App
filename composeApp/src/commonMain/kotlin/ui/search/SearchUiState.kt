package ui.search

import kotlinx.coroutines.flow.Flow
import data.model.Article

data class SearchUiState(
    val searchQuery: String = "",
    val articles: Flow<Article>? = null
)