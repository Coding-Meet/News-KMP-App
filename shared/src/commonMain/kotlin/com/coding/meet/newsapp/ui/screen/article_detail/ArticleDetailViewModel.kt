package com.coding.meet.newsapp.ui.screen.article_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.coding.meet.newsapp.domain.usecase.BookmarkArticleUseCase
import com.coding.meet.newsapp.domain.usecase.IsArticleBookmarkedUseCase
import com.coding.meet.newsapp.domain.usecase.RemoveBookmarkUseCase
import com.coding.meet.newsapp.ui.navigation.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val isArticleBookmarked: IsArticleBookmarkedUseCase,
    private val bookmarkArticle: BookmarkArticleUseCase,
    private val removeBookmark: RemoveBookmarkUseCase,
) : ViewModel() {

    val article = savedStateHandle.toRoute<Route.NewsDetail>().toArticle()
    private val _state = MutableStateFlow(ArticleDetailState(article = article, isLoading = true))
    val state = _state.asStateFlow()

    private val _effects = Channel<ArticleDetailEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        checkBookmarkStatus()
    }

    fun onEvent(event: ArticleDetailEvent) {
        when (event) {
            ArticleDetailEvent.ToggleBookmark -> toggleBookmark()
            ArticleDetailEvent.ShareArticle -> {
                val url = _state.value.article?.url ?: return
                viewModelScope.launch { _effects.send(ArticleDetailEffect.Share(url)) }
            }

            ArticleDetailEvent.OpenInBrowser -> {
                val url = _state.value.article?.url ?: return
                viewModelScope.launch { _effects.send(ArticleDetailEffect.OpenUrl(url)) }
            }

            ArticleDetailEvent.NavigateBack -> {
                viewModelScope.launch { _effects.send(ArticleDetailEffect.NavigateBack) }
            }
        }
    }

    private fun checkBookmarkStatus() {
        viewModelScope.launch {
            val bookmarked = isArticleBookmarked(article.publishedAt)
            _state.update { it.copy(isBookmarked = bookmarked, isLoading = false) }
        }
    }

    private fun toggleBookmark() {
        val currentArticle = _state.value.article ?: return
        viewModelScope.launch {
            if (_state.value.isBookmarked) {
                removeBookmark(currentArticle)
            } else {
                bookmarkArticle(currentArticle)
            }
            _state.update { it.copy(isBookmarked = !it.isBookmarked) }
        }
    }
}
