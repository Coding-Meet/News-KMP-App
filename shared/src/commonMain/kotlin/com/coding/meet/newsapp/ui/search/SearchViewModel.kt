package com.coding.meet.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meet.newsapp.domain.usecase.SearchNewsUseCase
import com.coding.meet.newsapp.utils.ArticleCache
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchNews: SearchNewsUseCase,
    private val articleCache: ArticleCache,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _effects = Channel<SearchEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.QueryChanged -> {
                _state.update { it.copy(query = event.query, isIdle = event.query.isBlank()) }
            }
            SearchEvent.Search -> performSearch()
            SearchEvent.Retry -> performSearch()
            is SearchEvent.OpenArticle -> {
                articleCache.put(event.article)
                viewModelScope.launch {
                    _effects.send(SearchEffect.NavigateToDetail(event.article.publishedAt))
                }
            }
        }
    }

    private fun performSearch() {
        val query = _state.value.query.trim()
        if (query.isBlank()) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, isIdle = false) }
            try {
                val articles = searchNews(query)
                _state.update { it.copy(articles = articles, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message ?: "Unknown error", isLoading = false) }
            }
        }
    }
}
