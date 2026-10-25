package com.coding.meet.newsapp.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meet.newsapp.domain.usecase.GetBookmarksUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val getBookmarks: GetBookmarksUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkState())
    val state = _state.asStateFlow()

    private val _effects = Channel<BookmarkEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        getBookmarks()
            .onStart { _state.update { it.copy(isLoading = true) } }
            .onEach { articles ->
                _state.update {
                    it.copy(
                        articles = articles,
                        isLoading = false
                    )
                }
            }
            .catch { e ->
                _state.update {
                    it.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.OpenArticle -> {
                viewModelScope.launch {
                    _effects.send(BookmarkEffect.NavigateToDetail(event.article))
                }
            }
        }
    }
}
