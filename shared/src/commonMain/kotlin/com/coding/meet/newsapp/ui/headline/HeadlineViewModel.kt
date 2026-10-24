package com.coding.meet.newsapp.ui.headline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meet.newsapp.domain.usecase.GetHeadlinesUseCase
import com.coding.meet.newsapp.utils.ArticleCache
import com.coding.meet.newsapp.utils.categoryList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HeadlineViewModel(
    private val getHeadlines: GetHeadlinesUseCase,
    private val articleCache: ArticleCache,
) : ViewModel() {

    private val _state = MutableStateFlow(HeadlineState(selectedCategory = categoryList[0]))
    val state = _state.asStateFlow()

    private val _effects = Channel<HeadlineEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        loadHeadlines(categoryList[0])
    }

    fun onEvent(event: HeadlineEvent) {
        when (event) {
            is HeadlineEvent.SelectCategory -> {
                if (event.category == _state.value.selectedCategory) return
                _state.update { it.copy(selectedCategory = event.category) }
                loadHeadlines(event.category)
            }
            HeadlineEvent.Retry -> loadHeadlines(_state.value.selectedCategory)
            is HeadlineEvent.OpenArticle -> {
                articleCache.put(event.article)
                viewModelScope.launch {
                    _effects.send(HeadlineEffect.NavigateToDetail(event.article.publishedAt))
                }
            }
        }
    }

    private fun loadHeadlines(category: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val articles = getHeadlines(category)
                _state.update { it.copy(articles = articles, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message ?: "Unknown error", isLoading = false) }
            }
        }
    }
}
