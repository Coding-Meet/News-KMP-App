package ui.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.NewsDatabase
import data.repository.NewsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BookmarkViewModel(newsDatabase: NewsDatabase) : ViewModel() {

    private val newsRepository = NewsRepository(newsDatabase.newsDao())

    private val _state = mutableStateOf(BookmarkUiState())
    val state: State<BookmarkUiState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        newsRepository.getArticles().onEach {
            _state.value = _state.value.copy(articles = it)
            println(it)
        }.launchIn(viewModelScope)
    }
}