package com.coding.meet.newsapp.ui.article_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meet.newsapp.domain.usecase.BookmarkArticleUseCase
import com.coding.meet.newsapp.domain.usecase.GetArticleByIdUseCase
import com.coding.meet.newsapp.domain.usecase.IsArticleBookmarkedUseCase
import com.coding.meet.newsapp.domain.usecase.RemoveBookmarkUseCase
import com.coding.meet.newsapp.utils.ArticleCache
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val articleId: String,
    private val articleCache: ArticleCache,
    private val getArticleById: GetArticleByIdUseCase,
    private val isArticleBookmarked: IsArticleBookmarkedUseCase,
    private val bookmarkArticle: BookmarkArticleUseCase,
    private val removeBookmark: RemoveBookmarkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ArticleDetailState())
    val state = _state.asStateFlow()

    private val _effects = Channel<ArticleDetailEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        loadArticle()
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

    private fun loadArticle() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Check in-memory cache first (covers headline/search articles not yet in DB)
            val article = articleCache.get(articleId) ?: getArticleById(articleId)
            val bookmarked = isArticleBookmarked(articleId)
            _state.update { it.copy(article = article, isBookmarked = bookmarked, isLoading = false) }
        }
    }

    private fun toggleBookmark() {
        val article = _state.value.article ?: return
        viewModelScope.launch {
            if (_state.value.isBookmarked) {
                removeBookmark(article)
            } else {
                bookmarkArticle(article)
            }
            _state.update { it.copy(isBookmarked = !it.isBookmarked) }
        }
    }
}
