package ui.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.NewsDatabase
import data.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel(
//    savedStateHandle: SavedStateHandle,
    newsDatabase: NewsDatabase
): ViewModel() {
    private val newsRepository = NewsRepository(newsDatabase.newsDao())
    var uiState by mutableStateOf(ArticleUiState())
        private set

//    init {
//        val data = savedStateHandle.get<String>(Constants.ARTICLE_ARG)
//        uiState = uiState.copy(article = data?.decodeFromString())
//        isArticleBookmark()
//    }


     fun isArticleBookmark() {
        viewModelScope.launch {
            newsRepository.getArticle(uiState.article.publishedAt)?.let {
                uiState = uiState.copy(isBookmarked = true)
            }
        }
    }

     fun bookmarkArticle() {
        viewModelScope.launch {
            if (!uiState.isBookmarked) uiState.article?.let { newsRepository.upsertArticle(it) }
            else uiState.article?.let { newsRepository.deleteArticle(it) }
            uiState = uiState.copy(isBookmarked = !uiState.isBookmarked)
        }
    }

}